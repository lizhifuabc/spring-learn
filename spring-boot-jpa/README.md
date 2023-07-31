# Spring Boot Jpa 框架基础

1. JPA
全称Java Persistence API，描述【对象-关系表】之间的映射关系（通过注解或者XM），并将实体对象持久化到数据库中。
有@Table、@Entity、@Column、@Transient等注解
JPA仅仅是一种规范，提供了一些接口，而接口需要实现才能工作；Hibernate就是实现了JPA接口的ORM框架

2. JPA 和 Hibernate     
JPA是一种ORM规范，Hibernate实现了JPA规范

3. Spring Data JPA
   Spring Data JPA 是spring提供的一套简化JPA开发的框架；
   Spring Data JPA 可以理解为 JPA 规范的再次封装抽象，底层还是使用了 Hibernate 的 JPA 技术实现。

## QueryDSL

JPA可以通过注解方式实现sql，对于一些复杂的sql语句用QueryDSL 来实现

apt-maven-plugin:1.1.3
mvn compile之后，会将带有@Entity注解的实体类在指定路径target/generated-sources/java下生成一个衍生的实体类，用这个衍生出来的实体类去构建动态查询的条件进行动态查询。

## 属性注解

`CascadeType` 是 JPA（Java Persistence API）中用于指定级联操作的枚举类型。它用于定义当执行某个操作（例如保存、更新、删除）时，对关联的实体类是否要进行相同的操作。通过设置合适的 `CascadeType`，可以简化对实体之间关系的管理，从而方便开发人员进行数据库操作。

`CascadeType` 枚举类型包含以下几个值：

1. `ALL`：表示所有操作都会级联。包括保存、更新、删除等操作，当对父实体执行操作时，其关联的子实体也会被相应地执行相同的操作。
2. `PERSIST`：表示只有保存操作会级联。当对父实体执行保存操作时，其关联的子实体也会被保存到数据库。
3. `MERGE`：表示只有更新操作会级联。当对父实体执行更新操作时，其关联的子实体也会被更新到数据库。
4. `REMOVE`：表示只有删除操作会级联。当对父实体执行删除操作时，其关联的子实体也会被从数据库中删除。
5. `REFRESH`：表示当父实体被刷新时，其关联的子实体也会被刷新。即从数据库重新加载关联的子实体。
6. `DETACH`：表示当父实体被分离（从持久化上下文中移除）时，其关联的子实体也会被分离。