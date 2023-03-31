package demo.cicd.versions;

import java.util.List;

import com.temenos.api.TField;
import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.currency.CurrencyRecord;

/**
 * TODO: Document me!
 *
 */
public class DemoCurrencyValidation extends RecordLifecycle {

    @Override
    public TValidationResponse validateRecord(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        CurrencyRecord curRec = new CurrencyRecord(currentRecord);
        List<TField> ccyNames = curRec.getCcyName();
        for (int i = 0; i < ccyNames.size(); i++) {
            TField ccyName = ccyNames.get(i);
            if ("TESTCCY".equals(ccyName.getValue())) {
                ccyName.setError("DEMO CICD: No TESTCCY value allowed");
                break;
            }
        }
        // TODO Auto-generated method stub
//        return super.validateRecord(application, currentRecordId, currentRecord, unauthorisedRecord, liveRecord,
//                transactionContext);
        return curRec.getValidationResponse();
    }

}
