package org.mifos.selfserviceapp.api;

import org.mifos.selfserviceapp.api.services.AuthenticationService;
import org.mifos.selfserviceapp.api.services.BeneficiaryService;
import org.mifos.selfserviceapp.api.services.ClientChargeService;
import org.mifos.selfserviceapp.api.services.ClientService;
import org.mifos.selfserviceapp.api.services.LoanAccountsListService;
import org.mifos.selfserviceapp.api.services.RecentTransactionsService;
import org.mifos.selfserviceapp.api.services.RegistrationService;
import org.mifos.selfserviceapp.api.services.SavingAccountsListService;
import org.mifos.selfserviceapp.api.services.ThirdPartyTransferService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author Vishwajeet
 * @since 13/6/16
 */
public class BaseApiManager {

    private static BaseURL baseUrl = new BaseURL();
    private static final String BASE_URL = baseUrl.getUrl();

    private static Retrofit retrofit;
    private static AuthenticationService authenticationApi;
    private static ClientService clientsApi;
    private static SavingAccountsListService savingAccountsListApi;
    private static LoanAccountsListService loanAccountsListApi;
    private static RecentTransactionsService recentTransactionsApi;
    private static ClientChargeService clientChargeApi;
    private static BeneficiaryService beneficiaryApi;
    private static ThirdPartyTransferService thirdPartyTransferApi;
    private static RegistrationService registrationApi;
    public BaseApiManager() {
        String authToken = "";
        createService(authToken);
    }

    private static void init() {
        authenticationApi = createApi(AuthenticationService.class);
        clientsApi = createApi(ClientService.class);
        savingAccountsListApi = createApi(SavingAccountsListService.class);
        loanAccountsListApi = createApi(LoanAccountsListService.class);
        recentTransactionsApi = createApi(RecentTransactionsService.class);
        clientChargeApi = createApi(ClientChargeService.class);
        beneficiaryApi = createApi(BeneficiaryService.class);
        thirdPartyTransferApi = createApi(ThirdPartyTransferService.class);
        registrationApi = createApi(RegistrationService.class);
    }

    private static <T> T createApi(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    public static void createService(String authToken) {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new SelfServiceOkHttpClient(authToken).getMifosOkHttpClient())
                .build();
        init();
    }

    public AuthenticationService getAuthenticationApi() {
        return authenticationApi;
    }

    public ClientService getClientsApi() {
        return clientsApi;
    }

    public SavingAccountsListService getSavingAccountsListApi() {
        return savingAccountsListApi;
    }

    public LoanAccountsListService getLoanAccountsListApi() {
        return loanAccountsListApi;
    }

    public RecentTransactionsService getRecentTransactionsApi() {
        return recentTransactionsApi;
    }

    public ClientChargeService getClientChargeApi() {
        return clientChargeApi;
    }

    public BeneficiaryService getBeneficiaryApi() {
        return beneficiaryApi;
    }

    public ThirdPartyTransferService getThirdPartyTransferApi() {
        return thirdPartyTransferApi;
    }

    public RegistrationService getRegistrationApi() {
        return registrationApi;
    }
}
