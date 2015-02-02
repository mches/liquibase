package liquibase.actionlogic.core;

import liquibase.Scope;
import liquibase.action.Action;
import liquibase.action.QuerySqlAction;
import liquibase.action.core.TableRowCountAction;
import liquibase.actionlogic.AbstractActionLogic;
import liquibase.actionlogic.ActionResult;
import liquibase.actionlogic.DelegateResult;
import liquibase.database.Database;
import liquibase.exception.ActionPerformException;
import liquibase.exception.ValidationErrors;

public class TableRowCountLogic extends AbstractActionLogic {

    @Override
    protected Class<? extends Action> getSupportedAction() {
        return TableRowCountAction.class;
    }

    @Override
    public ValidationErrors validate(Action action, Scope scope) {
        return super.validate(action, scope)
                .checkForRequiredField(TableRowCountAction.Attr.tableName, action);
    }

    @Override
    public ActionResult execute(Action action, Scope scope) throws ActionPerformException {
        Database database = scope.get(Scope.Attr.database, Database.class);
        return new DelegateResult(new QuerySqlAction("SELECT COUNT(*) FROM "+database.escapeTableName(
                action.get(TableRowCountAction.Attr.catalogName, String.class),
                action.get(TableRowCountAction.Attr.schemaName, String.class),
                action.get(TableRowCountAction.Attr.tableName, String.class))));
    }
}
