package liquibase.actionlogic.core.sybase;

import liquibase.Scope;
import liquibase.action.Action;
import liquibase.action.ExecuteSqlAction;
import liquibase.action.core.RenameTableAction;
import liquibase.actionlogic.ActionResult;
import liquibase.actionlogic.DelegateResult;
import liquibase.actionlogic.core.RenameTableLogic;
import liquibase.database.Database;
import liquibase.database.core.sybase.SybaseDatabase;
import liquibase.exception.ActionPerformException;

public class RenameTableLogicSybase extends RenameTableLogic {
    @Override
    protected Class<? extends Database> getRequiredDatabase() {
        return SybaseDatabase.class;
    }

    @Override
    public ActionResult execute(Action action, Scope scope) throws ActionPerformException {
        Database database = scope.get(Scope.Attr.database, Database.class);
        return new DelegateResult(new ExecuteSqlAction(
                "exec sp_rename '"
                        + database.escapeTableName(action.get(RenameTableAction.Attr.catalogName, String.class),
                        action.get(RenameTableAction.Attr.schemaName, String.class),
                        action.get(RenameTableAction.Attr.oldTableName, String.class))
                        + "', '"
                        + action.get(RenameTableAction.Attr.newTableName, String.class)
                        + "'"));
    }
}
