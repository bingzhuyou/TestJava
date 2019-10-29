package com.chaos.graphql;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import graphql.GraphQL;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

public class GraphQLDemoTest {

	public static void main(String[] args) {
		CPU cpu = new CPU();
		cpu.setName("I7");
		cpu.setCache("19M");

		Memory m1 = new Memory();
		m1.setName("海盗船");
		m1.setSize("8G");

		Memory m2 = new Memory();
		m2.setName("海盗船");
		m2.setSize("8G");

		List<Memory> memoryList = new ArrayList<Memory>();
		memoryList.add(m1);
		memoryList.add(m2);

		Computer computer = new Computer();
		computer.setName("组装机");
		computer.setCpu(cpu);
		computer.setMemoryList(memoryList);

		// 定义GraphQL类型
		GraphQLObjectType cpuType = newObject().name("cpuType").field(newFieldDefinition().name("name").type(
				GraphQLString)).field(newFieldDefinition().name("cache").type(GraphQLString)).build();

		GraphQLObjectType memoryType = newObject().name("memoryType").field(newFieldDefinition().name("name").type(
				GraphQLString)).field(newFieldDefinition().name("size").type(GraphQLString)).build();

		GraphQLObjectType computerType = newObject().name("computerType").field(newFieldDefinition().name("name").type(
				GraphQLString)).field(newFieldDefinition().name("cpu").type(cpuType)).field(newFieldDefinition().name(
						"memoryList").type(new GraphQLList(memoryType))).build();

		// 关联返回类型与返回数据
		@SuppressWarnings("deprecation")
		GraphQLObjectType queryType = newObject().name("computerQuery").field(newFieldDefinition().type(
				computerType).name("computer").dataFetcher(evn -> {
					return computer;
				})).build();

		GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).build();

		GraphQL graphQL = GraphQL.newGraphQL(schema).build();

		Map<String, Object> result = graphQL.execute(
				"{computer{name,cpu{name,cache},memoryList{name,size}}}").getData();

		// 打印返回结果
		System.out.println(result);

		Map<String, Object> result2 = graphQL.execute(
				"{computer{name,cpu{name,cache}}}").getData();

		// 打印返回结果
		System.out.println(result2);

	}

}