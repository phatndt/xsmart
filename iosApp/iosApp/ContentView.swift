import SwiftUI
import commonKit

struct ContentView: View {
	let greet = "Hello world, 2026!"

	var body: some View {
		Text(greet)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
