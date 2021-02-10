import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { IoHome, IoSettingsOutline, IoSettings, IoLayersOutline, IoLayers } from 'react-icons/io5';
import HomeScreen from './screens/HomeScreen';
import LayerScreen from './screens/LayerScreen';
import SettingScreen from './screens/SettingScreen';

const Tab = createBottomTabNavigator();

export default class App extends React.PureComponent {
  render() {
    return (
      <NavigationContainer>
        <Tab.Navigator
          screenOptions={({ route }) => ({
            tabBarIcon: ({ focused, color, size }) => {
              let icons = {
                Home: <IoHome size={size} color={color} />,
                Layer: focused ? <IoLayers size={size} color={color} /> : <IoLayersOutline size={size} color={color} />,
                Setting: focused ? (
                  <IoSettings size={size} color={color} />
                ) : (
                  <IoSettingsOutline size={size} color={color} />
                ),
              };
              return icons[route.name];
            },
          })}
          tabBarOptions={{
            showLabel: false,
            activeTintColor: 'tomato',
            inactiveTintColor: 'gray',
          }}
        >
          <Tab.Screen name="Home" component={HomeScreen} />
          <Tab.Screen name="Layer" component={LayerScreen} />
          <Tab.Screen name="Setting" component={SettingScreen} />
        </Tab.Navigator>
      </NavigationContainer>
    );
  }
}
