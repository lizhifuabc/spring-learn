# flyway

版本迁移以V开头，只会执行一次；回退迁移以U开头，一般不使用；可重复执行迁移以R开头，每次修改后都会重新执行

注意：

由于创建 flyway_schema_history 的版本是1，所以迁移文件的版本必须从不能是V1开始，否则不会执行