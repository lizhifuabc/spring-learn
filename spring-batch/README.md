# 轻量级的批量处理框架

Spring Batch 使用数据库来保存任务执行的状态和进度信息。

它默认会在启动时自动创建一系列表用于存储这些信息。主要的表有:

1. BATCH_JOB_INSTANCE:存储每次 Job 执行的实例信息。
2. BATCH_JOB_EXECUTION:存储每个 Job 实例的执行信息。
3. BATCH_JOB_EXECUTION_CONTEXT:存储每个 Job 执行的上下文信息。
4. BATCH_JOB_EXECUTION_PARAMS:存储每个 Job 执行的输入参数。
5. BATCH_STEP_EXECUTION:存储每个 Job 中每个 Step 的执行信息。 
6. BATCH_STEP_EXECUTION_CONTEXT:存储每个 Step 执行的上下文信息。
7. BATCH_JOB_SEQences:存储批处理元数据的序列号,用于唯一标识执行实例。

这些表记录了所有的 Job 和 Step 的执行信息,包括:

- 执行开始时间和结束时间
- 执行状态(STARTED、STOPPED、FAILED、COMPLETED等) 

- 读入和输出的记录数
- 执行期间产生的异常等信息有了这些信息

主要作用：

- 重新启动失败的 Job 和 Step
- 统计 Job 和 Step 的运行信息和性能
- 审计批处理的执行活动 
- 定制 Job 和 Step 的执行逻辑等

## 案例

读取CSV文件的数据,进行处理后输出到数据库表中


https://spring.io/guides/gs/batch-processing/