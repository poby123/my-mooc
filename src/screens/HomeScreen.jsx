import React from 'react';
import { IconButton } from '@material-ui/core';
import UpdateIcon from '@material-ui/icons/Update';
import { createStackNavigator } from '@react-navigation/stack';

import ListComponent from '../components/ListComponent';
// import BoardScreen from './BoardScreen';
import BoardComponent from '../components/BoardComponent';

function HomeScreen(props) {
  return <ListComponent {...props} />;
}

const HomeStack = createStackNavigator();
export default function HomeStackScreen() {
  return (
    <HomeStack.Navigator initialRouteName="Home">
      <HomeStack.Screen
        name="Home"
        component={HomeScreen}
        options={{
          headerTitle: 'MOOC',
          headerRight: () => (
            <IconButton onClick={() => console.log('pressed')}>
              <UpdateIcon />
            </IconButton>
          ),
          headerStyle: {
            backgroundColor: '#2399E7',
          },
          headerTintColor: '#FFFFFF',
          headerTitleStyle: {
            fontFamily: 'Nanum Gothic',
            fontWeight: 'bold',
          },
        }}
      />
      <HomeStack.Screen
        name="Board"
        component={BoardComponent}
        options={{
          headerTitle: 'MOOC',
          headerRight: () => (
            <IconButton onClick={() => console.log('pressed')}>
              <UpdateIcon />
            </IconButton>
          ),
          headerStyle: {
            backgroundColor: '#2399E7',
          },
          headerTintColor: '#FFFFFF',
          headerTitleStyle: {
            fontFamily: 'Nanum Gothic',
            fontWeight: 'bold',
          },
        }}
      />
    </HomeStack.Navigator>
  );
}
