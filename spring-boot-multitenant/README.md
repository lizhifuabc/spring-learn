# 多租户

1. 不同数据源
   这种方式为每个租户使用独立的数据源,实现最高级别的隔离。
   我们可以使用Hibernate的MultiTenantConnectionProvider接口实现数据源的选择逻辑。
2. 不同Schema
   这种方式将所有租户放在同一个数据源下,但每个租户拥有独立的Schema。
   我们需要在创建SessionFactory时为每个租户指定Schema,Hibernate会自动使用对应租户的Schema。
3. 表名策略
   这种方式将所有租户放在同一个数据源和Schema下,通过表名来区分租户。
   我们需要实现自定义的表命名策略,为每个表名加上租户ID前缀。Hibernate在创建表时会使用这个策略生成带前缀的表名。
4. 租户ID字段
   这种方式将所有租户的数据放在同一数据源、Schema和表下,仅使用租户ID字段来区分。
   我们需要在实体上添加租户ID的属性,Hibernate会为该属性指定条件,过滤出当前租户的数据。
5. 筛选器
   这种方式依然使用共享的资源,通过Hibernate Filter实现数据筛选。
   我们需要实现自定义的Hibernate Filter,在初始化过滤器时传入当前租户ID,Hibernate会使用该Filter条件过滤数据。

## 实现

1. 自定义MultiTenantConnectionProvider实现多数据源
2. @TenantId用于租户字段策略
3. 实现自定义TableNameResolver用于表名策略
4. 实现自定义Hibernate Filter用于筛选器策略

