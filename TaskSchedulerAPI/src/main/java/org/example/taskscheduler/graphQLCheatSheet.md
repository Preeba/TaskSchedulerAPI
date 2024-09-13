GraphQL Cheatsheet

1. Add 4 modules to start with:
   * Config
   * Resolver
   * Service
   * Model
   
2. Under resources, add module "graphql" and add schema.graphqls file to define the schema
   * Add the schema - types, enums, input, query, mutation - use clear naming conventions (e.g., PascalCase for types, camelCase for fields).

3. Create resolvers for each query and mutation, handling the business logic. Also, need to implement custom logic for the DateTime scalar to ensure proper serialization and deserialization of date/time values



Related
How can I implement recurring tasks in a GraphQL schema
What are the best practices for defining priority levels in a GraphQL schema
How do I model dependencies between tasks in a GraphQL schema
Can you provide an example of a GraphQL schema with recurring tasks and dependencies
How do I handle task dependencies in a GraphQL mutation
