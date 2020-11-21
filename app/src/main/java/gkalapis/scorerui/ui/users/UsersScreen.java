package gkalapis.scorerui.ui.users;

import java.util.List;

import gkalapis.scorerui.model.api.User;
import gkalapis.scorerui.ui.common.CommonScreen;

public interface UsersScreen extends CommonScreen {

    void showUsers(List<User> users);
}
