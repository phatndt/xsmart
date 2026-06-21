import SwiftUI
import DiKitWrapper

@main
struct iOSApp: App {
    
    init() {
        initDi()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
