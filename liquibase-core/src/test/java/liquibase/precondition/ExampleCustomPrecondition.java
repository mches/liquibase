package liquibase.precondition;

import liquibase.database.Database;
import liquibase.exception.CustomPreconditionFailedException;
import liquibase.logging.LogFactory;

public class ExampleCustomPrecondition implements CustomPrecondition {

    private String name;
    private String count;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public void check(Database database) throws CustomPreconditionFailedException {
        LogFactory.getInstance().getLog().info("Custom precondition ran. Name: "+name+", count: "+count  );

//        throw new CustomPreconditionFailedException("custom precondition failed", new RuntimeException());
    }
}
