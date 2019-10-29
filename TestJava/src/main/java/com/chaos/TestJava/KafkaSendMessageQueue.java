package com.chaos.TestJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class KafkaSendMessageQueue extends Thread {
	// private static final int HEARTBEAT_MESSAGE_TYPE = 999999;
	// private static final int SLEEP_TIME_MS = 300;
	//
	// private final Integer message_send_check_max_wait_time_ms = 5 * 1000;
	//
	// private final Integer message_send_queue_max_size = 5000;
	// private final Integer message_resend_max_size = 5;
	// private final Integer message_resend_max_time_ms = 15 * 60 * 1000;
	// private final Integer enable_send_max_queue_size;
	private boolean running = false;
	private final LinkedBlockingQueue<String> sendMessageQueue;

	private final AtomicBoolean runingReSendFlag = new AtomicBoolean(false);
	private final AtomicBoolean needReSendBegin = new AtomicBoolean(false);
	private boolean close = false;

	private AtomicLong heartbeatMessageSerialNOGenerator = new AtomicLong(0);
	private final String topicName;
	// private final byte[] heartbeatMessageByteArray;
	private long lastSendMessageSerialNO = -1;
	// private long lastCheckTimeMS;

	public KafkaSendMessageQueue() {
		topicName = "kkkk";
		sendMessageQueue = new LinkedBlockingQueue<String>();
	}

	public void init() throws Exception {

	}

	public int getPartitionListSize() {
		return 12;
	}

	public void addSendMessage(String sendMessageWarpper) {

		int dataLength = 0;
		boolean sendHeartbeatMessageFlag = false;
		long serialNO = -1;
		try {

			if (!sendHeartbeatMessageFlag) {
				this.lastSendMessageSerialNO = serialNO;
			}
			int tryCount = 0;
			boolean putSendQueueFlag = false;
			long initSendTimeMS = System.currentTimeMillis();
			long currentResendTimeMS = System.currentTimeMillis();
			long usingTimeMS = 0L;
			while (!putSendQueueFlag && (this.close == false)) {
				boolean needResendFlag = needReSendBegin.get();
				if (tryCount > 6) {

					currentResendTimeMS = System.currentTimeMillis();
					usingTimeMS = currentResendTimeMS - initSendTimeMS;
					System.out.println("addSendMessage,tryCount:" + tryCount + ",usingTimeMS:" + usingTimeMS
							+ ",needResendFlag:" + needResendFlag + ",sendHeartbeatMessageFlag:"
							+ sendHeartbeatMessageFlag + ",header:" + ",dataLength:" + dataLength
							+ ",topicName:" + topicName);
					if (sendHeartbeatMessageFlag) {
						// 如果死心跳消息，跳出循环，方能触发重发逻辑
						break;
					} else if (tryCount == 1000) {
						System.out.println("addSendMessage fail.,tryCount:" + tryCount + ",usingTimeMS:"
								+ usingTimeMS + ",needResendFlag:" + needResendFlag + ",sendHeartbeatMessageFlag:"
								+ sendHeartbeatMessageFlag + ",header:" + ",dataLength:" + dataLength
								+ ",topicName:" + topicName);
						break;
					}

					Thread.sleep(100);
				}
				if (needResendFlag) {
					resend();
				} else {
					tryCount++;
					putSendQueueFlag = putSendMessageQueueAndSend(sendMessageWarpper);
				}
				if (!putSendQueueFlag) {
					Thread.sleep(100);
				}
			}

		} catch (Exception e) {
			System.out.println("addSendMessage Exception.header:" + ",dataLength:" + dataLength
					+ ",serialNO:" + serialNO + "\n" + e.toString());
		} catch (Throwable e) {
			System.out.println("addSendMessage Throwable.header:" + ",dataLength:" + dataLength
					+ ",serialNO:" + serialNO + "\n" + e.toString());
		}
	}

	private boolean putSendMessageQueueAndSend(String sendMessageWarpper) {
		boolean putFlag = false;
		try {

			if (sendMessageWarpper == null) {
				System.out.println("putSendMessageQueueAndSend,sendMessageWarpper is null.kafkaSenderConfig:");
				return true;
			}

			putFlag = this.sendMessageQueue.offer(sendMessageWarpper, 1000, TimeUnit.MICROSECONDS);

			// int putcount = sendMessageWarpper.addPutCount(putFlag);
			if (putFlag) {

				int sendMessageQueueSize = sendMessageQueue.size();
				// KafkaSendMessageWarpper kafkaSendMessageWarpper =
				// this.kafkaProducerWarpper.sendKafkaMessage(
				// sendMessageWarpper);

				// TODO throw a exception

				if (!randomBoolean()) {
					System.out.println("putSendMessageQueueAndSend,offer return true.putcount,"
							+ ",sendMessageQueueSize:" + sendMessageQueueSize + ",sendMessageWarpper:"
							+ sendMessageWarpper + ",topicName:" + this.topicName);
					return true;
				} else {
					System.out.println(
							"putSendMessageQueueAndSend,offer return true,but kafkaProducerWarpper.sendKafkaMessage return null.putcount,"
									+ ",sendMessageQueueSize:"
									+ sendMessageQueueSize + ",sendMessageWarpper:" + sendMessageWarpper + ",topicName:"
									+ this.topicName);
					throw new Exception();
				}

			} else {
				int sendMessageQueueSize = sendMessageQueue.size();
				String head_KafkaSendMessageWarpper = sendMessageQueue.peek();
				System.out.println("putSendMessageQueueAndSend,sendMessageQueue offer return false.putcount,"
						+ ",sendMessageQueueSize:" + sendMessageQueueSize + ",head:"
						+ head_KafkaSendMessageWarpper + ",sendMessageWarpper:" + sendMessageWarpper + ",topicName:"
						+ this.topicName);
				Thread.sleep(10);
				return false;
			}
		} catch (Exception e) {
			System.out.println("putSendMessageQueueAndSend Exception.kafkaSenderConfig:" + e);
		} catch (Throwable e) {
			System.out.println("putSendMessageQueueAndSend Exception.kafkaSenderConfig:" + e);
		}
		return putFlag;

	}

	private void putSendMessageQueueAndSendForReSend(String sendMessageWarpper) {
		boolean putFlag = false;
		try {

			while (!putFlag) {

				putFlag = this.sendMessageQueue.offer(sendMessageWarpper, 1000, TimeUnit.MICROSECONDS);
				if (!putFlag) {
					System.out.println("putSendMessageQueueAndSendForReSend  offer return false.topicName:"
							+ this.topicName + ",sendMessageWarpper:" + sendMessageWarpper);
					Thread.sleep(1000L);
				} else {
					System.out.println("putSendMessageQueueAndSendForReSend  offer return true..topicName:"
							+ this.topicName + ",sendMessageWarpper:" + sendMessageWarpper + ", size: "
							+ sendMessageQueue.size());
				}
			}
			// TODO throw a exception
			// this.kafkaProducerWarpper.sendKafkaMessage(sendMessageWarpper);
		} catch (Exception e) {
			System.out.println("putSendMessageQueueAndSendForReSend Exception.topicName:" + this.topicName
					+ ",sendMessageWarpper:" + sendMessageWarpper + "\n " + e);
		} catch (Throwable e) {
			System.out.println("putSendMessageQueueAndSendForReSend Exception..topicName:" + this.topicName
					+ ",sendMessageWarpper:" + sendMessageWarpper + "\n " + e);
		}

	}

	public void run() {
		long lastSendHeartbeatMessage = 0;
		try {

			// NeedReSendMessageResult arg_needReSendMessageResult = new
			// NeedReSendMessageResult();
			// NeedReSendMessageResult return_needReSendMessageResult = null;
			while (!close) {
				try {
					this.running = true;
					// this.lastCheckTimeMS = System.currentTimeMillis();
					Thread.sleep(300);
					int sendMessageQueueSize = this.sendMessageQueue.size();
					boolean temp_runingReSendFlag = runingReSendFlag.get();
					System.out.println("SendMessageQueue,runingReSendFlag:" + temp_runingReSendFlag + ",topicName:"
							+ this.topicName + ",sendMessageQueueSize:" + sendMessageQueueSize
							+ ",needReSendMessageResult:");
					if (temp_runingReSendFlag) {
						continue;
					}
					// return_needReSendMessageResult =
					// isNeedReSendMessage(arg_needReSendMessageResult);
					// boolean return_needReSendMessageResult = false;
					// if (return_needReSendMessageResult != null &&
					// return_needReSendMessageResult.isNeedReSendFlag()) {
					if (randomBoolean()) {
						System.out.println("SendMessageQueue needReSend,return_needReSendMessageResult:"
								+ ",topicName:" + this.topicName);
						needReSendBegin.set(true);
					} else {
						sendMessageQueue.clear();
					}
					long currentTimeMillis = System.currentTimeMillis();
					long usingTime = currentTimeMillis - lastSendHeartbeatMessage;
					if ((!close) && usingTime > 60 * 1000) {
						lastSendHeartbeatMessage = currentTimeMillis;
						sendHeartbeatMessage();
					}

				} catch (Exception e) {
					System.out.println("SendMessageQueue run Exception.topicName:" + topicName + "" + e);
				} catch (Throwable e) {
					System.out.println("SendMessageQueue run Throwable.topicName:" + topicName + "" + e);
				}

			}
			this.running = false;
		} catch (Exception e) {
			System.out.println("SendMessageQueue run Exception.kafkaSenderConfig:" + "" + e);
		} catch (Throwable e) {
			System.out.println("SendMessageQueue run Throwable.kafkaSenderConfig:" + "" + e);
		} finally {
			System.out.println("SendMessageQueue run end.close:" + close + ",lastSendHeartbeatMessage:"
					+ lastSendHeartbeatMessage + ",,kafkaSenderConfig:");
			this.running = false;
		}

	}

	private boolean randomBoolean() {
		int random = (int) (Math.random() * 10);

		if (random % 331 == 0) {
			return true;
		}
		return false;
	}

	private void sendHeartbeatMessage() {
		long sendMessageKey = heartbeatMessageSerialNOGenerator.get();
		// MessageHeader header = new MessageHeader(sendMessageKey,
		// HEARTBEAT_MESSAGE_TYPE);
		Map<String, String> attributeMap = new HashMap<String, String>();
		attributeMap.put("lastSendMessageSerialNO", lastSendMessageSerialNO + "");
		int sendMessageQueueSize = sendMessageQueue.size();
		attributeMap.put("sendMessageQueueSize", sendMessageQueueSize + "");
		// header.setAttributeMap(attributeMap);
		// KafkaSendMessageWarpper kafkaSendMessageWarpper =
		// createSendMessageWarpper(header, heartbeatMessageByteArray,
		// sendMessageKey);

		String kafkaSendMessageWarpper = "heartbeat";
		// kafkaSendMessageWarpper.setSendHeartbeatMessageFlag(true);
		this.addSendMessage(kafkaSendMessageWarpper);
	}

	// private KafkaSendMessageWarpper createSendMessageWarpper(MessageHeader
	// header, byte[] data, long serialNO) {
	//
	// header.setProducerId(this.kafkaSenderConfig.getProducerId());
	// header.setSerialNO(serialNO);
	// header.setTimestamp(System.currentTimeMillis());
	//
	// KafkaSendMessageWarpper sendMessageWarpper = new
	// KafkaSendMessageWarpper(serialNO, this.topicName, header,
	// data);
	// return sendMessageWarpper;
	// }

	private byte[] getHeartbeatMessageByteArray() {
		String heartbeatMessage = "heartbeatMessage";
		byte[] heartbeatMessageByteArray = null;
		try {
			heartbeatMessageByteArray = heartbeatMessage.getBytes();
			heartbeatMessageByteArray = heartbeatMessage.getBytes("UTF-8");
		} catch (Exception e) {
			System.out.println("getHeartbeatMessageByteArray run Exception." + "\n" + e);
		}
		return heartbeatMessageByteArray;
	}

	public boolean isRunning() {
		return running;
	}

	public boolean isClose() {
		return close;
	}

	private void resend() {

		boolean temp_needReSendBegin = needReSendBegin.get();
		boolean temp_runingReSendFlag = runingReSendFlag.get();
		if (temp_runingReSendFlag == false) {

		}
		int needResendQueueSize = sendMessageQueue.size();
		boolean initFlag = false;
		try {
			// initFlag = kafkaProducerWarpper.init();
			initFlag = true;
		} catch (Exception e) {
			System.out.println("kafkaProducerWarpper init Fail.needResendQueueSize:" + needResendQueueSize
					+ ",topicName:"
					+ this.topicName + ",runingReSendFlag" + temp_runingReSendFlag
					+ ",needReSendBegin:" + temp_needReSendBegin + "\n" + e);
		} catch (Throwable e) {
			System.out.println("kafkaProducerWarpper init Fail.needResendQueueSize:" + needResendQueueSize
					+ ",topicName:"
					+ this.topicName + ",runingReSendFlag" + temp_runingReSendFlag
					+ ",needReSendBegin:" + temp_needReSendBegin + "\n" + e);
		}
		if (!initFlag) {
			System.out.println("kafkaProducerWarpper init Fail.needResendQueueSize:" + needResendQueueSize
					+ ",topicName:"
					+ this.topicName + ",runingReSendFlag" + temp_runingReSendFlag
					+ ",needReSendBegin:" + temp_needReSendBegin);
			return;
		}
		String head_needSendMessageWarppers = null;
		try {

			runingReSendFlag.set(true);
			head_needSendMessageWarppers = sendMessageQueue.peek();
			System.out.println("kafkaProducerWarpper resend.needResendQueueSize:" + needResendQueueSize + ",topicName:"
					+ this.topicName + ",head:" + head_needSendMessageWarppers);
			String needSendMessageWarppers = null;

			List<String> need_resend_message_list = new ArrayList<String>(
					needResendQueueSize);
			while ((!close) && (!sendMessageQueue.isEmpty())) {
				try {
					needSendMessageWarppers = sendMessageQueue.poll();
					if (needSendMessageWarppers != null) {
						need_resend_message_list.add(needSendMessageWarppers);
					}
				} catch (Exception e) {
					System.out.println("resend Exception." + "\n" + e);
				} catch (Throwable e) {
					System.out.println("resend Throwable." + "\n" + e);
				}

			}

			int need_resend_message_list_size = need_resend_message_list.size();

			for (int i = 0; i < need_resend_message_list_size; i++) {
				needSendMessageWarppers = need_resend_message_list.get(i);
				putSendMessageQueueAndSendForReSend(needSendMessageWarppers);
			}
		} catch (Exception e) {

			System.out.println("resend Exception." + this.topicName + ",head:" + head_needSendMessageWarppers + "\n"
					+ e);
		} catch (Throwable e) {

			System.out.println("resend Throwable." + this.topicName + ",head:" + head_needSendMessageWarppers + "\n"
					+ e);
		} finally {
			runingReSendFlag.set(false);
			needReSendBegin.set(false);
		}

	}

	// public NeedReSendMessageResult
	// isNeedReSendMessage(NeedReSendMessageResult needReSendMessageResult) {
	//
	// try {
	// do {
	// if (this.close) {
	// System.out.println("isNeedReSendMessage,close is
	// true.needReSendMessageResult:"
	// + needReSendMessageResult
	// + ",");
	// return null;
	// }
	// needReSendMessageResult =
	// checkNeedResendByBatch(needReSendMessageResult);
	// if (needReSendMessageResult == null) {
	// return null;
	// }
	//
	// if (needReSendMessageResult.isNeedReSendFlag()) {
	// break;
	// }
	//
	// } while (needReSendMessageResult != null &&
	// (needReSendMessageResult.isNeedReSendFlag() == false));
	//
	// } catch (Exception e) {
	// logger.error("isNeedReSendMessage Exception,topicName:" + this.topicName
	// + ",needReSendMessageResult:"
	// + needReSendMessageResult, e);
	//
	// } catch (Throwable e) {
	// logger.error("isNeedReSendMessage Throwable.topicName:" + this.topicName
	// + ",needReSendMessageResult:"
	// + needReSendMessageResult, e);
	//
	// }
	//
	// return needReSendMessageResult;
	//
	// }
	//
	// private NeedReSendMessageResult
	// checkNeedResendByBatch(NeedReSendMessageResult needReSendMessageResult) {
	// RecordMetadata recordMetadata = null;
	// Throwable checkException = null;
	// int checkCount = 0;
	// KafkaSendMessageWarpper sendMessageWarpper = null;
	// boolean needTryChec = false;
	// try {
	// if (needReSendMessageResult == null) {
	// needReSendMessageResult = new NeedReSendMessageResult();
	// }
	// sendMessageWarpper = sendMessageQueue.peek();
	// if (sendMessageWarpper == null) {
	// // 如果当前发送队里为空，休眠
	// // logger.info("checkNeedResend,sendMessageQueue is isEmpty." +
	// // sendMessageQueue.size());
	// try {
	// Thread.sleep(SLEEP_TIME_MS);
	// } catch (InterruptedException e) {
	// logger.info("checkNeedResend,Thread sleep InterruptedException" +
	// sendMessageQueue.size(), e);
	// }
	// needReSendMessageResult.sendMessageQueueISEmpty();
	// return needReSendMessageResult;
	// }
	//
	// if (sendMessageWarpper.isSendFlag()) {
	//
	// needReSendMessageResult.notNeedResend(sendMessageQueue,
	// sendMessageWarpper, "sendFlagIsTrue");
	// return needReSendMessageResult;
	// }
	//
	// checkCount = sendMessageWarpper.check();
	// if (sendMessageWarpper.isReSendEndFlag()) {
	//
	// needReSendMessageResult.notNeedResend(sendMessageQueue,
	// sendMessageWarpper, "ReSendEnd(" + checkCount
	// + ")");
	// return needReSendMessageResult;
	// }
	//
	// Future<RecordMetadata> future =
	// sendMessageWarpper.getSendMessageFuture();
	//
	// if (future == null) {
	//
	// if (checkCount < message_resend_max_size) {
	// Thread.sleep(1000L);
	// String sendFailReason = "futureIsNull(" + checkCount + " lt " +
	// message_resend_max_size + ")";
	// sendMessageWarpper.sendFail(sendFailReason, checkException);
	// needReSendMessageResult.needTryCheck(sendFailReason);
	// return needReSendMessageResult;
	// } else {
	// sendMessageWarpper.sendFail("future is null.and checkCount gt " +
	// message_resend_max_size, null);
	// needReSendMessageResult.needResend(sendMessageWarpper, "futureIsNull(" +
	// checkCount + " eq "
	// + message_resend_max_size + ")");
	// return needReSendMessageResult;
	// }
	// }
	//
	// recordMetadata = future.get(message_send_check_max_wait_time_ms,
	// TimeUnit.MILLISECONDS);
	//
	// if (recordMetadata != null) {
	//
	// long checksum = recordMetadata.checksum();
	// int return_partition = recordMetadata.partition();
	// long return_offset = recordMetadata.offset();
	//
	// sendMessageWarpper.sendSuccess(return_partition, return_offset);
	//
	// kafkaSendResultLogger.info("checkSendMessage,send message
	// success.checkCount:" + checkCount
	// + ",checksum:" + checksum + ",sendMessage:" + sendMessageWarpper +
	// ",return_partition:"
	// + return_partition + ",return_offset:" + return_offset);
	// needReSendMessageResult.notNeedResend(sendMessageQueue,
	// sendMessageWarpper, "checkSuccess#"
	// + return_partition + "#" + return_offset);
	//
	// return needReSendMessageResult;
	// }
	// } catch (TimeoutException e) {
	// kafkaSendResultLogger.error("checkSendMessage,future get
	// TimeoutException.checkCount:" + checkCount
	// + ",sendMessage:" + sendMessageWarpper + ",topicName:" + this.topicName,
	// e);
	// needTryChec = true;
	// } catch (InterruptedException e) {
	// kafkaSendResultLogger.error("checkSendMessage
	// InterruptedException.sendMessage:" + sendMessageWarpper
	// + ",topicName:" + this.topicName, e);
	// checkException = e;
	// } catch (ExecutionException e) {
	// kafkaSendResultLogger.error("checkSendMessage Execution
	// Exception.sendMessage:" + sendMessageWarpper
	// + ",topicName:" + this.topicName, e);
	// checkException = e;
	// } catch (org.apache.kafka.common.errors.NetworkException e) {
	// kafkaSendResultLogger.error("checkSendMessage
	// NetworkException.sendMessage:" + sendMessageWarpper
	// + ",topicName:" + this.topicName, e);
	// checkException = e;
	// } catch (Exception e) {
	// kafkaSendResultLogger.error("checkSendMessage Exception.sendMessage:" +
	// sendMessageWarpper + ",topicName:"
	// + this.topicName, e);
	//
	// checkException = e;
	// } catch (Throwable e) {
	// kafkaSendResultLogger.error("checkSendMessage Throwable.sendMessage:" +
	// sendMessageWarpper + ",topicName:"
	// + this.topicName, e);
	// checkException = e;
	// }
	// if (needTryChec) {
	// needReSendMessageResult.needTryCheck("needTryCheck#" + checkCount);
	// return needReSendMessageResult;
	// }
	// String checkExceptionName = null;
	// if (checkException != null) {
	// checkExceptionName = checkException.getClass().getSimpleName();
	// }
	//
	// boolean needReSendByExecption = reSendCheckByExecption(checkException,
	// sendMessageWarpper);
	// if (sendMessageWarpper != null) {
	// sendMessageWarpper.sendFail("sendFailByExecption,needReSendFlag:" +
	// needReSendByExecption + "#"
	// + checkExceptionName, checkException);
	// }
	// if (needReSendByExecption) {
	// needReSendMessageResult.needResend(sendMessageWarpper,
	// "needReSendByExecption#" + checkExceptionName);
	// return needReSendMessageResult;
	// } else {
	// kafkaSendResultLogger.error("checkSendMessage InterruptedException." +
	// checkExceptionName + ",sendMessage:"
	// + sendMessageWarpper, checkException);
	// if (needReSendMessageResult != null) {
	// needReSendMessageResult.notNeedResend(sendMessageQueue,
	// sendMessageWarpper, "notNeedReSendByExecption#"
	// + checkExceptionName);
	// }
	// return needReSendMessageResult;
	// }
	//
	// }

	private boolean reSendCheckByExecption(Throwable exception, String sendMessageWarpper) {

		if (exception == null) {
			return false;
		}
		// *

		// // Non-Retriable exceptions (fatal, the message will never be sent):
		// if (exception instanceof InvalidTopicException) {
		// // Non-Retriable exceptions (fatal, the message will never be sent):
		// // * InvalidTopicException
		// kafkaSendResultLogger.error("isNeedSend
		// InterruptedException.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		//
		// return false;
		// }
		// if (exception instanceof OffsetMetadataTooLargeException) {
		// // Non-Retriable exceptions (fatal, the message will never be sent):
		// // * OffsetMetadataTooLargeException
		// kafkaSendResultLogger.error("isNeedSend
		// OffsetMetadataTooLargeException.sendMessageWarpper:"
		// + sendMessageWarpper + ",topicName:" + this.topicName, exception);
		// return false;
		// }
		// if (exception instanceof RecordBatchTooLargeException) {
		// // Non-Retriable exceptions (fatal, the message will never be sent):
		// // * RecordBatchTooLargeException
		// kafkaSendResultLogger.error("isNeedSend
		// RecordBatchTooLargeException.sendMessageWarpper:"
		// + sendMessageWarpper + ",topicName:" + this.topicName, exception);
		// return false;
		// }
		// if (exception instanceof RecordBatchTooLargeException) {
		// // Non-Retriable exceptions (fatal, the message will never be sent):
		// // * RecordTooLargeException
		// kafkaSendResultLogger.error("isNeedSend
		// RecordBatchTooLargeException.sendMessageWarpper:"
		// + sendMessageWarpper + ",topicName:" + this.topicName, exception);
		// return false;
		// }
		// if (exception instanceof UnknownServerException) {
		//
		// // * UnknownServerException
		// kafkaSendResultLogger.error("isNeedSend
		// UnknownServerException.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		// return false;
		// }
		//
		// // *
		// // * Retriable exceptions (transient, may be covered by increasing
		// // #.retries):
		//
		// if (exception instanceof CorruptRecordException) {
		//
		// // * CorruptRecordException
		// kafkaSendResultLogger.error("isNeedSend
		// CorruptRecordException.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		// return true;
		// }
		// if (exception instanceof InvalidMetadataException) {
		//
		// // * InvalidMetadataException
		// kafkaSendResultLogger.error("isNeedSend
		// InvalidMetadataException.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		// return true;
		// }
		// if (exception instanceof NotEnoughReplicasAfterAppendException) {
		//
		// // * NotEnoughReplicasAfterAppendException
		// kafkaSendResultLogger.error("isNeedSend
		// NotEnoughReplicasAfterAppendException.sendMessageWarpper:"
		// + sendMessageWarpper + ",topicName:" + this.topicName, exception);
		// return true;
		// }
		// if (exception instanceof NotEnoughReplicasException) {
		//
		// // * NotEnoughReplicasException
		// kafkaSendResultLogger.error("isNeedSend
		// NotEnoughReplicasException.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		// return true;
		// }
		// if (exception instanceof OffsetOutOfRangeException) {
		//
		// // * OffsetOutOfRangeException
		// kafkaSendResultLogger.error("isNeedSend
		// OffsetOutOfRangeException.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		// return false;
		// }
		//
		// if (exception instanceof UnknownTopicOrPartitionException) {
		//
		// // * UnknownTopicOrPartitionException
		// kafkaSendResultLogger.error("isNeedSend
		// UnknownTopicOrPartitionException.sendMessageWarpper:"
		// + sendMessageWarpper + ",topicName:" + this.topicName, exception);
		// return false;
		// }
		// if (exception instanceof RetriableException) {
		//
		// // * RetriableException
		// kafkaSendResultLogger.error("isNeedSend
		// RetriableException.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		// return true;
		// }
		//
		// if (exception instanceof
		// org.apache.kafka.common.errors.TimeoutException) {
		//
		// // * TimeoutException
		// kafkaSendResultLogger.error("isNeedSend
		// TimeoutException.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		// return true;
		// }
		//
		// if (exception instanceof
		// org.apache.kafka.common.errors.NetworkException) {
		// // * NetworkException
		// kafkaSendResultLogger.error("isNeedSend
		// NetworkException.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		// return true;
		// }
		//
		// // *未知异常
		// // *
		//
		// if (exception instanceof ExecutionException) {
		// kafkaSendResultLogger.error("isNeedSend
		// ExecutionException.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		// return false;
		// }
		//
		// if (exception instanceof Exception) {
		// kafkaSendResultLogger.error("isNeedSend
		// Exception.sendMessageWarpper:" + sendMessageWarpper + ",topicName:"
		// + this.topicName, exception);
		// return true;
		// }
		// if (exception instanceof Throwable) {
		// kafkaSendResultLogger.error("isNeedSend
		// Throwable.sendMessageWarpper:" + sendMessageWarpper + ",topicName:"
		// + this.topicName, exception);
		// return true;
		// }
		// kafkaSendResultLogger.error("isNeedSend unKnow
		// Exception.sendMessageWarpper:" + sendMessageWarpper
		// + ",topicName:" + this.topicName, exception);
		return true;

	}

	public void closeKafkaSendMessageQueue() {
		try {
			// logger.error("closeKafkaSendMessageQueue
			// begin,kafkaSenderConfig:" + kafkaSenderConfig);
			this.close = true;
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("closeKafkaSendMessageQueue Exception,kafkaSenderConfig:" + e);
		} catch (Throwable e) {
			System.out.println("closeKafkaSendMessageQueue Throwable,kafkaSenderConfig:" + e);
		} finally {
			System.out.println("closeKafkaSendMessageQueue setClose end,kafkaSenderConfig:");
		}
		// try {
		// System.out.println("closeKafkaSendMessageQueue
		// begin,kafkaSenderConfig:" );
		//// this.kafkaProducerWarpper.closeKafkaProducerWarpper();
		// } catch (Exception e) {
		// logger.error("close Exception,kafkaSenderConfig:" + e);
		// } catch (Throwable e) {
		// logger.error("close Throwable,kafkaSenderConfig:" + e);
		// } finally {
		// logger.info("closeKafkaSendMessageQueue closeKafkaProducerWarpper
		// end,kafkaSenderConfig:"
		// + kafkaSenderConfig);
		// }
	}

	public String performance() {
		StringBuilder stringBuilder = new StringBuilder();
		// if (kafkaSenderConfig != null) {
		// stringBuilder.append("KafkaSendMessageQueue[" +
		// this.topicName).append("#").append(
		// kafkaSenderConfig.getProducerId());
		// }

		stringBuilder.append(",lastSendMessageSerialNO:").append(this.lastSendMessageSerialNO);
		int sendMessageQueueSize = -1;
		if (this.sendMessageQueue != null) {
			sendMessageQueueSize = this.sendMessageQueue.size();
		}

		stringBuilder.append(",sendMessageQueueSize:").append(sendMessageQueueSize);
		return stringBuilder.toString();
	}
}