package com.chaos.TestJava.AlarmRuleEngine;

import java.util.Map;

//import com.boco.xdpp.model.alarm.filter.core.AlgorithmBase;

public class RuleAlgorithm_0_20180210230805709_1564779 extends AlgorithmBase<Map> {
	public RuleAlgorithm_0_20180210230805709_1564779() {
		super.fielddef = new int[1200010];
		super.InitAlgorithmBase();
		int iPos = 0;
		addFieldDef(64, 3, iPos++);
		addFieldDef(5, 3, iPos++);
		addFieldDef(65, 8, iPos++);
		addFieldDef(6, 4, iPos++);
		addFieldDef(8, 5, iPos++);
		addFieldDef(1008, 5, iPos++);
		addFieldDef(9, 4, iPos++);
		addFieldDef(10, 4, iPos++);
		addFieldDef(11, 4, iPos++);
		addFieldDef(101102, 8, iPos++);
		addFieldDef(12, 4, iPos++);
		addFieldDef(101103, 4, iPos++);
		addFieldDef(13, 5, iPos++);
		addFieldDef(14, 4, iPos++);
		addFieldDef(85, 4, iPos++);
		addFieldDef(101106, 8, iPos++);
		addFieldDef(110701, 4, iPos++);
		addFieldDef(101105, 8, iPos++);
		addFieldDef(86, 4, iPos++);
		addFieldDef(101104, 4, iPos++);
		addFieldDef(81, 4, iPos++);
		addFieldDef(1200006, 4, iPos++);
		addFieldDef(1200007, 4, iPos++);
		addFieldDef(22, 4, iPos++);
		addFieldDef(92, 4, iPos++);
		addFieldDef(1200008, 8, iPos++);
		addFieldDef(1200009, 4, iPos++);
		addFieldDef(89, 3, iPos++);
		addFieldDef(31, 4, iPos++);
		addFieldDef(30, 4, iPos++);
		addFieldDef(516, 4, iPos++);
		addFieldDef(517, 4, iPos++);
		addFieldDef(509, 3, iPos++);
		addFieldDef(510, 4, iPos++);
		addFieldDef(519, 8, iPos++);
		addFieldDef(512, 3, iPos++);
		addFieldDef(514, 8, iPos++);
		addFieldDef(46, 3, iPos++);
		addFieldDef(51, 4, iPos++);
		addFieldDef(49, 3, iPos++);
		addFieldDef(54, 4, iPos++);
		addFieldDef(100401, 4, iPos++);
		addFieldDef(62, 4, iPos++);
	}

	public void initalarmvalue(String alarmId, Map e) {
		Object fieldValue = null;
		fieldValue = e.get("standard_alarm_name");
		if (fieldValue != null) {
			regula_expression(alarmId, 64, -1, 3, "standard_alarm_name", fieldValue);
		}
		fieldValue = e.get("alarm_text");
		if (fieldValue != null) {
			regula_expression(alarmId, 5, -1, 3, "alarm_text", fieldValue);
		}
		fieldValue = e.get("standard_alarm_id");
		Comma_expression_8_9(alarmId, 65, 8, "standard_alarm_id", fieldValue);
		fieldValue = e.get("redefine_severity");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 6, 4, "redefine_severity", fieldValue);
		}
		fieldValue = e.get("int_id");
		if (fieldValue != null) {
			f_negroup(alarmId, 8, 5, "int_id", fieldValue);
		}
		fieldValue = e.get("eqp_int_id");
		if (fieldValue != null) {
			f_negroup(alarmId, 1008, 5, "eqp_int_id", fieldValue);
		}
		fieldValue = e.get("object_class");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 9, 4, "object_class", fieldValue);
		}
		fieldValue = e.get("vendor_id");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 10, 4, "vendor_id", fieldValue);
		}
		fieldValue = e.get("org_severity");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 11, 4, "org_severity", fieldValue);
		}
		fieldValue = e.get("gcss_client_name");
		Comma_expression_8_9(alarmId, 101102, 8, "gcss_client_name", fieldValue);
		fieldValue = e.get("org_type");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 12, 4, "org_type", fieldValue);
		}
		fieldValue = e.get("gcss_client_level");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 101103, 4, "gcss_client_level", fieldValue);
		}
		fieldValue = e.get("alarm_title");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 13, 5, "alarm_title", fieldValue);
		}
		fieldValue = e.get("active_status");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 14, 4, "active_status", fieldValue);
		}
		fieldValue = e.get("professional_type");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 85, 4, "professional_type", fieldValue);
		}
		fieldValue = e.get("ne_ip");
		Comma_expression_8_9(alarmId, 101106, 8, "ne_ip", fieldValue);
		fieldValue = e.get("layer_rate");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 110701, 4, "layer_rate", fieldValue);
		}
		fieldValue = e.get("business_system");
		Comma_expression_8_9(alarmId, 101105, 8, "business_system", fieldValue);
		fieldValue = e.get("standard_flag");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 86, 4, "standard_flag", fieldValue);
		}
		fieldValue = e.get("gcss_service_level");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 101104, 4, "gcss_service_level", fieldValue);
		}
		fieldValue = e.get("alarm_resource_status");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 81, 4, "alarm_resource_status", fieldValue);
		}
		fieldValue = e.get("network_type");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 1200006, 4, "network_type", fieldValue);
		}
		fieldValue = e.get("network_type_top");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 1200007, 4, "network_type_top", fieldValue);
		}
		fieldValue = e.get("province_id");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 22, 4, "province_id", fieldValue);
		}
		fieldValue = e.get("ne_sub_type");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 92, 4, "ne_sub_type", fieldValue);
		}
		fieldValue = e.get("ems_id");
		Comma_expression_8_9(alarmId, 1200008, 8, "ems_id", fieldValue);
		fieldValue = e.get("group_id");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 1200009, 4, "group_id", fieldValue);
		}
		fieldValue = e.get("circuit_no");
		if (fieldValue != null) {
			regula_expression(alarmId, 89, -1, 3, "circuit_no", fieldValue);
		}
		fieldValue = e.get("city_id");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 31, 4, "city_id", fieldValue);
		}
		fieldValue = e.get("sub_alarm_type");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 30, 4, "sub_alarm_type", fieldValue);
		}
		fieldValue = e.get("business_layer");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 516, 4, "business_layer", fieldValue);
		}
		fieldValue = e.get("board_type");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 517, 4, "board_type", fieldValue);
		}
		fieldValue = e.get("title_text");
		if (fieldValue != null) {
			regula_expression(alarmId, 509, -1, 3, "title_text", fieldValue);
		}
		fieldValue = e.get("interrupt_circuit_state");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 510, 4, "interrupt_circuit_state", fieldValue);
		}
		fieldValue = e.get("vendor_type");
		Comma_expression_8_9(alarmId, 519, 8, "vendor_type", fieldValue);
		fieldValue = e.get("alarm_unit");
		if (fieldValue != null) {
			regula_expression(alarmId, 512, -1, 3, "alarm_unit", fieldValue);
		}
		fieldValue = e.get("gcss_service_state");
		Comma_expression_8_9(alarmId, 514, 8, "gcss_service_state", fieldValue);
		fieldValue = e.get("alarm_source");
		if (fieldValue != null) {
			regula_expression(alarmId, 46, -1, 3, "alarm_source", fieldValue);
		}
		fieldValue = e.get("eqp_object_class");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 51, 4, "eqp_object_class", fieldValue);
		}
		fieldValue = e.get("eqp_label");
		if (fieldValue != null) {
			regula_expression(alarmId, 49, -1, 3, "eqp_label", fieldValue);
		}
		fieldValue = e.get("ne_status");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 54, 4, "ne_status", fieldValue);
		}
		fieldValue = e.get("object_level");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 100401, 4, "object_level", fieldValue);
		}
		fieldValue = e.get("logic_sub_alarm_type");
		if (fieldValue != null) {
			Comma_expression_b(alarmId, 62, 4, "logic_sub_alarm_type", fieldValue);
		}
	}

	public void pre4() {
		rb.pre3_1(64, getPos(64));
		rb.pre3_1(5, getPos(5));
		rb.pre3_1(89, getPos(89));
		rb.pre3_1(509, getPos(509));
		rb.pre3_1(512, getPos(512));
		rb.pre3_1(46, getPos(46));
		rb.pre3_1(49, getPos(49));
	}
}
