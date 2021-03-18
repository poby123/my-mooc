import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import SigninComponent from '../components/SigninComponent';
import SignupComponent from '../components/SignupComponent';
import HeaderOptions from '../HeaderOptions';
import TabMenus from './TabMenus';

const Stack = createStackNavigator();

class AuthMenus extends React.PureComponent {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <Stack.Navigator initialRouteName="TabMenus">
        <Stack.Screen
          name="Signin"
          component={SigninComponent}
          options={{
            ...HeaderOptions,
          }}
        />
        <Stack.Screen
          name="Signup"
          component={SignupComponent}
          options={{
            ...HeaderOptions,
          }}/>
        <Stack.Screen
          name="TabMenus"
          component={TabMenus}
          options={{
            headerShown: false,
          }}
        />
      </Stack.Navigator>
    );
  }
}

export default AuthMenus;