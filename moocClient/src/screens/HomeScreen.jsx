import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import {IconButton} from '@material-ui/core';
import UpdateIcon from '@material-ui/icons/Update';
import HeaderOptions from '../HeaderOptions';
import ListComponent from '../components/ListComponent';
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
          ...HeaderOptions,
          headerRight: () => (
            <IconButton onClick={() => console.log('pressed')}>
              <UpdateIcon />
            </IconButton>
          ),
        }}
      />
      <HomeStack.Screen name="Board" component={BoardComponent} options={HeaderOptions} />
    </HomeStack.Navigator>
  );
}
