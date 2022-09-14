package genericpubsub;

import org.apache.avro.Schema;

import com.salesforce.eventbus.protobuf.SchemaInfo;
import com.salesforce.eventbus.protobuf.SchemaRequest;
import com.salesforce.eventbus.protobuf.TopicInfo;
import com.salesforce.eventbus.protobuf.TopicRequest;

import utility.CommonContext;
import utility.ExampleConfigurations;

/**
 * An example that retrieves the topic info of a single-topic.
 *
 * Example:
 * ./run.sh genericpubsub.GetTopic
 *
 * @author sidd0610
 */
public class GetTopic extends CommonContext {

    public GetTopic(final ExampleConfigurations options) {
        super(options);
    }

    private void getTopic(String topicName) {
        // Use the GetTopic RPC to get the topic info for the given topicName.
        TopicInfo topicInfo = blockingStub.getTopic(TopicRequest.newBuilder().setTopicName(topicName).build());

        logger.info("Topic Details:");
        topicInfo.getAllFields().entrySet().forEach(item -> {
            logger.info(item.getKey() + " : " + item.getValue());
        });
    }

    public static void main(String[] args) {

        try {
            final ExampleConfigurations exampleConfigurations = new ExampleConfigurations("arguments.yaml");

            // Using the try-with-resource statement. The CommonContext class implements AutoCloseable in
            // order to close the resources used.
            try (GetTopic example = new GetTopic(exampleConfigurations)) {
                example.getTopic(exampleConfigurations.getTopic());
            } catch (Exception e) {
                CommonContext.printStatusRuntimeException("Error while Getting Topic", e);
                System.exit(1);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}
