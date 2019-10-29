package com.chaos.TestJava;

import java.util.Date;

import javax.swing.JOptionPane;

public class TomatoClock {

	@SuppressWarnings("deprecation")
	public static int setAlarmClock() {
		String inputValue = JOptionPane.showInputDialog("Please input alarm clock waitting time(minutes) or alarm clock time(HH:MI).");
		int sleepMin = 0;

		if (inputValue == null || inputValue.length() == 0) {
			return sleepMin;
		}

		try {
			if (inputValue.indexOf(':') > 0 && inputValue.length() == 5) {
				Date dNow = new Date();

				int hour = Integer.parseInt(inputValue.substring(0, 2));
				int min = Integer.parseInt(inputValue.substring(3, 5));

				if (hour < 24 && hour > 0 && min > 0 && min < 60) {
					if (hour > dNow.getHours()) {
						sleepMin = (hour - dNow.getHours()) * 60 + min - dNow.getMinutes();
					} else if (hour == dNow.getHours()) {
						if (min > dNow.getMinutes()) {
							sleepMin = min - dNow.getMinutes();
						} else if (min < dNow.getMinutes()) {
							sleepMin = 24 * 60 + min - dNow.getMinutes();
						} else {
							JOptionPane.showMessageDialog(null, "Sleep 0 minutes!Please reset.", "alert", JOptionPane.ERROR_MESSAGE);
							setAlarmClock();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bad time format : " + inputValue + ", time format is HH:MI, for example : 10:21.", "alert", JOptionPane.ERROR_MESSAGE);
					setAlarmClock();
				}
			} else {
				sleepMin = Integer.parseInt(inputValue);

			}
		} catch (Exception e) {
			setAlarmClock();
		}

		return sleepMin;
	}

	public static void tomatoClock(String arguments) {
		Object[] options = { "OK", "CANCEL", "SET" };
		int selectedValue = JOptionPane.showOptionDialog(null, "Tomato clock begin! Default is 25 mins.", "TomatoClock", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);

		// System.out.println(selectedValue);
		if (selectedValue == 0) {
			try {
				Thread.sleep(25 * 60 * 1000);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			}
			tomatoClock("OK");
		} else if (selectedValue == 2) {
			int sleepMin = setAlarmClock();
			System.out.println("Set clock to " + sleepMin + " minutes.");
			try {
				Thread.sleep(sleepMin * 60 * 1000);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			}
			tomatoClock("OK");
		} else {
			JOptionPane.showMessageDialog(null, "Have a nice day!", "info", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public static void main(String args[]) {
		tomatoClock("OK");
	}
}
