package marchenko.com.diplomameteors;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import marchenko.com.diplomameteors.ui.ui_general.ExpeditionListActivity;

/**
 * Он принимает объект вызывающего его Активити и список.
 * Запускает ActionMode. Задает его титул, связывает его с конкретным menu.xml
 * Устанавливает, чтобы по длинному нажатию на элемент списка, он выделялся, а другие выделения отменялись.
 * Когда кликается menuItem (удалить или редактировать..), то тут вызывается метод
 * (который прописан в MainActivity) для этого menuItem и для выделенного элемента списка (по position)
 */
public class ActionModeHelper implements ActionMode.Callback,
        AdapterView.OnItemLongClickListener {
    ExpeditionListActivity host;
    ActionMode activeMode;
    ListView modeView;

    public ActionModeHelper(final ExpeditionListActivity host, ListView modeView) {
        this.host = host;
        this.modeView = modeView;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> view, View row,
                                   int position, long id) {
        modeView.clearChoices();
        modeView.setItemChecked(position, true);

        if (activeMode == null) {
            activeMode = host.startActionMode(this);
        }

        return (true);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = host.getMenuInflater();

        inflater.inflate(R.menu.menu_actions_mode, menu);
        mode.setTitle(R.string.header_expedition);

        return (true);
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return (false);
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        boolean result =
                host.performAction(item.getItemId(),
                        modeView.getCheckedItemPosition());

        if (item.getItemId() == R.id.menu_delete) {
            activeMode.finish();
        }

        return (result);
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        activeMode = null;
        modeView.clearChoices();
        modeView.requestLayout();
    }
}