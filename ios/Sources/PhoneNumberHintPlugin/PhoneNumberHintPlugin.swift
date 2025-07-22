import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(PhoneNumberHintPlugin)
public class PhoneNumberHintPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "PhoneNumberHintPlugin"
    public let jsName = "PhoneNumberHint"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = PhoneNumberHint()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
}
