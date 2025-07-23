// package com.mwt.plugins.phonenumberhint;

// import com.getcapacitor.JSObject;
// import com.getcapacitor.Plugin;
// import com.getcapacitor.PluginCall;
// import com.getcapacitor.PluginMethod;
// import com.getcapacitor.annotation.ActivityCallback;
// import com.getcapacitor.annotation.CapacitorPlugin;

// import android.app.Activity;
// import android.content.Intent;
// import android.util.Log;  // <-- Import Log class
// import com.getcapacitor.annotation.CapacitorPlugin;
// import com.getcapacitor.annotation.ActivityCallback;

// //import main.java.com.mwt.plugins.phonenumberhint.PhoneNumberHintHelper;

// @CapacitorPlugin(name = "PhoneNumberHint")
// public class PhoneNumberHintPlugin extends Plugin {

//     private PhoneNumberHint implementation = new PhoneNumberHint();

//     @PluginMethod
//     public void echo(PluginCall call) {
//         String value = call.getString("value");

//         JSObject ret = new JSObject();
//         ret.put("value", implementation.echo(value));
//         call.resolve(ret);
//     }

//     // new code

//     //  private PhoneNumberHintHelper helper;
//     // private static final String TAG = "PhoneNumberHintPlugin";

//     // @Override
//     // public void load() {
//     //     helper = new PhoneNumberHintHelper(getActivity());
//     //     Log.d(TAG, "Plugin loaded and helper initialized.");
//     // }

//     // @PluginMethod
//     // public void getPhoneNumber(PluginCall call) {
//     //     Log.d(TAG, "getPhoneNumber called.");
//     //     helper.getPhoneNumberHint(call, this); // FIXED: pass `this` (Plugin)
//     // }

//     // @ActivityCallback
//     // private void handlePhoneNumberResult(PluginCall call, Activity activity, int resultCode, Intent data) {
//     //     Log.d(TAG, "onActivityResult callback received.");
//     //     helper.handlePhoneNumberResult(call, activity, resultCode, data);
//     // }

//     private static final String TAG = "PhoneNumberHintPlugin";
//     private PhoneNumberHintHelper helper;

//     @Override
//     public void load() {
//         helper = new PhoneNumberHintHelper(getActivity());
//         Log.d(TAG, "Helper initialized");
//     }

//     @PluginMethod
//     public void getPhoneNumber(PluginCall call) {
//         Log.d(TAG, "getPhoneNumber()");
//         helper.getPhoneNumberHint(call, this);
//     }

//     @ActivityCallback
//     private void handlePhoneNumberResult(PluginCall call, Activity activity, int resultCode, Intent intent) {
//         Log.d(TAG, "Activity callback received");
//         helper.handlePhoneNumberResult(call, activity, resultCode, intent);
//     }
// }


// android/src/main/java/com/example/phonenumberhint/PhoneNumberHintPlugin.java



//============================================= working code ===========================================

// package com.mwt.plugins.phonenumberhint;
// //package com.yourdomain.phonenumberhint;

// import android.app.Activity;
// import android.content.Intent;
// import android.content.IntentSender;
// import androidx.activity.result.ActivityResult;
// import androidx.activity.result.IntentSenderRequest;
// import androidx.activity.result.ActivityResultCallback;
// import androidx.activity.result.ActivityResultLauncher;

// import com.getcapacitor.Bridge;
// import com.getcapacitor.JSObject;
// import com.getcapacitor.Plugin;
// import com.getcapacitor.PluginCall;
// import com.getcapacitor.PluginMethod;
// import com.getcapacitor.annotation.CapacitorPlugin;

// import com.google.android.gms.auth.api.identity.Identity;
// import com.google.android.gms.auth.api.identity.SignInClient;
// import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;

// @CapacitorPlugin(name = "PhoneNumberHint")
// public class PhoneNumberHintPlugin extends Plugin {
//   private PluginCall savedCall;
//   private ActivityResultLauncher<IntentSenderRequest> hintLauncher;

//   @Override
//   public void load() {
//     Bridge bridge = getBridge();
//     hintLauncher = bridge.registerForActivityResult(
//       new androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult(),
//       new ActivityResultCallback<ActivityResult>() {
//         @Override
//         public void onActivityResult(ActivityResult result) {
//           if (savedCall == null) return;

//           if (result.getResultCode() != Activity.RESULT_OK || result.getData() == null) {
//             savedCall.reject("No phone selected");
//             savedCall = null;
//             return;
//           }
//           Intent data = result.getData();


//           String number = data.getStringExtra("phone_number");
//           if (number == null) {
//             savedCall.reject("No phone number returned");
//           } else {
//             JSObject ret = new JSObject();
//             ret.put("phoneNumber", number);
//             savedCall.resolve(ret);
//           }
//           savedCall = null;
//         }
//       }
//     );
//   }

//   @PluginMethod
//   public void getPhoneNumber(PluginCall call) {
//     Activity activity = getActivity();
//     if (activity == null) {
//       call.reject("Activity not available");
//       return;
//     }
//     savedCall = call;

//     SignInClient client = Identity.getSignInClient(activity);
//     GetPhoneNumberHintIntentRequest req = GetPhoneNumberHintIntentRequest.builder().build();

//     client.getPhoneNumberHintIntent(req)
//       .addOnSuccessListener(intentResult -> {
//         IntentSenderRequest isr = new IntentSenderRequest.Builder(
//           intentResult.getIntentSender()
//         ).build();
//         hintLauncher.launch(isr);
//       })
//       .addOnFailureListener(e -> {
//         savedCall.reject("Unable to get hint intent", e);
//         savedCall = null;
//       });
//   }
// }



//============================================= working code ===========================================



// new code 2 aslo partially working
// package com.mwt.plugins.phonenumberhint;
// import android.app.Activity;
// import android.content.Intent;
// import android.content.IntentSender;

// import androidx.activity.result.ActivityResult;
// import androidx.activity.result.ActivityResultCallback;
// import androidx.activity.result.ActivityResultLauncher;
// import androidx.activity.result.IntentSenderRequest;

// import com.getcapacitor.Bridge;
// import com.getcapacitor.JSObject;
// import com.getcapacitor.Plugin;
// import com.getcapacitor.PluginCall;
// import com.getcapacitor.PluginMethod;
// import com.getcapacitor.annotation.CapacitorPlugin;

// import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
// import com.google.android.gms.auth.api.identity.Identity;
// import com.google.android.gms.auth.api.identity.SignInClient;
// import com.google.android.gms.auth.api.identity.SignInCredential;

// @CapacitorPlugin(name = "PhoneNumberHint")
// public class PhoneNumberHintPlugin extends Plugin {
//     private PluginCall savedCall;
//     private ActivityResultLauncher<IntentSenderRequest> hintLauncher;

//     @Override
//     public void load() {
//         Bridge bridge = getBridge();
//         hintLauncher = bridge.registerForActivityResult(
//             new androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult(),
//             new ActivityResultCallback<ActivityResult>() {
//                 @Override
//                 public void onActivityResult(ActivityResult result) {
//                     if (savedCall == null) return;

//                     if (result.getResultCode() != Activity.RESULT_OK || result.getData() == null) {
//                         savedCall.reject("No phone selected");
//                         savedCall = null;
//                         return;
//                     }

//                     try {
//                         SignInCredential credential = Identity.getSignInClient(getActivity())
//                             .getSignInCredentialFromIntent(result.getData());
//                         String phoneNumber = credential.getPhoneNumber();

//                         if (phoneNumber == null || phoneNumber.isEmpty()) {
//                             savedCall.reject("No phone number returned by Identity API");
//                         } else {
//                             JSObject ret = new JSObject();
//                             ret.put("phoneNumber", phoneNumber);
//                             savedCall.resolve(ret);
//                         }
//                     } catch (Exception e) {
//                         savedCall.reject("Failed to extract phone number", e);
//                     }

//                     savedCall = null;
//                 }
//             }
//         );
//     }

//     @PluginMethod
//     public void getPhoneNumber(PluginCall call) {
//         Activity activity = getActivity();
//         if (activity == null) {
//             call.reject("Activity not available");
//             return;
//         }

//         savedCall = call;

//         SignInClient client = Identity.getSignInClient(activity);
//         GetPhoneNumberHintIntentRequest request = GetPhoneNumberHintIntentRequest.builder().build();

//         client.getPhoneNumberHintIntent(request)
//             .addOnSuccessListener(result -> {
//               IntentSenderRequest isr = new IntentSenderRequest.Builder(
//                   result.getIntentSender()
//               ).build();
//               hintLauncher.launch(isr);
//             })
//             .addOnFailureListener(e -> {
//                 call.reject("Unable to get phone number hint intent", e);
//                 savedCall = null;
//             });
//     }
// }

// new code 2


// new code 3
package com.mwt.plugins.phonenumberhint;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;

import com.getcapacitor.Bridge;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.common.api.ApiException;

@CapacitorPlugin(name = "PhoneNumberHint")
public class PhoneNumberHintPlugin extends Plugin {

    private PluginCall savedCall;
    private ActivityResultLauncher<IntentSenderRequest> hintLauncher;

    @Override
    public void load() {
        hintLauncher = getBridge().registerForActivityResult(
            new ActivityResultContracts.StartIntentSenderForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (savedCall == null) return;

                    Intent data = result.getData();
                    if (result.getResultCode() != Activity.RESULT_OK || data == null) {
                        savedCall.reject("User cancelled or no phone number selected");
                        savedCall = null;
                        return;
                    }

                    try {
                        String phoneNumber = Identity.getSignInClient(getActivity())
                                .getPhoneNumberFromIntent(data);

                        if (phoneNumber != null && !phoneNumber.isEmpty()) {
                            JSObject ret = new JSObject();
                            ret.put("phoneNumber", phoneNumber);
                            savedCall.resolve(ret);
                        } else {
                            savedCall.reject("Phone number not available");
                        }
                    } catch (ApiException e) {
                        Log.e("PhoneNumberHint", "Phone Number Hint failed", e);
                        savedCall.reject("Google Identity API exception", e);
                    } catch (Exception e) {
                        Log.e("PhoneNumberHint", "Unexpected error", e);
                        savedCall.reject("Unexpected error extracting phone number", e);
                    }

                    savedCall = null;
                }
            }
        );
    }

    @PluginMethod
    public void getPhoneNumber(PluginCall call) {
        Activity activity = getActivity();
        if (activity == null) {
            call.reject("Activity is not available");
            return;
        }

        savedCall = call;
        SignInClient client = Identity.getSignInClient(activity);
        GetPhoneNumberHintIntentRequest request = GetPhoneNumberHintIntentRequest.builder().build();

        client.getPhoneNumberHintIntent(request)
            .addOnSuccessListener(result -> {
              IntentSenderRequest isr = new IntentSenderRequest.Builder(result.getIntentSender()).build();
              hintLauncher.launch(isr);
            })
            .addOnFailureListener(e -> {
                Log.e("PhoneNumberHint", "Could not get phone number hint intent", e);
                call.reject("Could not get phone number hint intent", e);
                savedCall = null;
            });
    }
}

// new code 3


