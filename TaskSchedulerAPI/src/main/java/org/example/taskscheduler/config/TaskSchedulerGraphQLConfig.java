package org.example.taskscheduler.config;

import graphql.GraphQL;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.example.taskscheduler.resolver.TaskResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.io.InputStream;

@Configuration
public class TaskSchedulerGraphQLConfig {

    @Bean
    public GraphQL graphQL(GraphQLSchema graphQLSchema) {
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.DateTime)
                //Add other custom scalars here
                .build();
    }

    @Bean
    public GraphQLSchema graphQLSchema(TaskResolver taskResolver) {
        // Load schema definition from file
        InputStream schemaStream = getClass().getResourceAsStream("/schema.graphqls");
        if (schemaStream == null) {
            throw new RuntimeException("Schema file not found");
        }

        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaStream);

        // Create runtime wiring
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        .dataFetcher("task", taskResolver::task)
                        .dataFetcher("tasks", taskResolver::tasks)
                        .dataFetcher("nextTaskToExecute", taskResolver::nextTaskToExecute)
                )
                .type("Mutation", builder -> builder
                        .dataFetcher("createTask", taskResolver::createTask)
                        .dataFetcher("updateTask", taskResolver::updateTask)
                        .dataFetcher("deleteTask", taskResolver::deleteTask)
                        .dataFetcher("markTaskCompleted", taskResolver::markTaskCompleted)
                        .dataFetcher("rescheduleTask", taskResolver::rescheduleTask)
                )
                .build();

        // Generate schema
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }
}
