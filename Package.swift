// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "Phonenumberhint",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "Phonenumberhint",
            targets: ["PhoneNumberHintPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "PhoneNumberHintPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/PhoneNumberHintPlugin"),
        .testTarget(
            name: "PhoneNumberHintPluginTests",
            dependencies: ["PhoneNumberHintPlugin"],
            path: "ios/Tests/PhoneNumberHintPluginTests")
    ]
)