// In App.js in a new project
import * as React from "react";
import { View, Text, StyleSheet } from "react-native";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";

const styles = StyleSheet.create({
	container: {
		height: "100vh",
		alignItems: "center",
		justifyContent: "center",
  },
});

function HomeScreen() {
	return (
		<View style={styles.container}>
			<Text>Home Screen</Text>
		</View>
	);
}

const Stack = createStackNavigator();

function App() {
	return (
		<NavigationContainer>
			<Stack.Navigator>
				<Stack.Screen name="Home" component={HomeScreen} />
			</Stack.Navigator>
		</NavigationContainer>
	);
}

export default App;
