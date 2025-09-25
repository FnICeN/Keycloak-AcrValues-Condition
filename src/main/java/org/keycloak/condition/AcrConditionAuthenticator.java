package org.keycloak.condition;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticator;
import org.keycloak.models.AuthenticatorConfigModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class AcrConditionAuthenticator implements ConditionalAuthenticator {
    @Override
    public boolean matchCondition(AuthenticationFlowContext authenticationFlowContext) {
        String send_acr_values = authenticationFlowContext.getAuthenticationSession().getClientNote("acr_values");
        if (send_acr_values == null) {
            System.out.println("未收到任何acr_values");
            return false;
        }
        else System.out.println("收到acr_values：" + send_acr_values);
        AuthenticatorConfigModel config = authenticationFlowContext.getAuthenticatorConfig();
        String need_acr_values =config.getConfig().get("set.acr.values");
        if (need_acr_values == null) {
            System.out.println("未读取任何acr_values");
            return false;
        }
        else System.out.println("读取配置的acr_values：" + need_acr_values);
        return send_acr_values.equals(need_acr_values);
    }

    @Override
    public void action(AuthenticationFlowContext authenticationFlowContext) {

    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    @Override
    public void close() {

    }
}
