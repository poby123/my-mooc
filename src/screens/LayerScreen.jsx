import React from 'react';
import { Text, View, TouchableHighlight, ScrollView, StyleSheet } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';

const mockData = [
  {
    id: '1',
    title: '공지사항',
    link: '',
  },
  {
    id: '2',
    title: '소프트웨어학부',
    link: '',
  },
];

const styles = StyleSheet.create({
  container: {
    backgroundColor: 'white',
    marginTop: '0.3em',
    marginBottom: '0.3em',
    marginLeft: '0.5em',
    marginRight: '0.5em',
    padding: '1em',
    fontFamily: 'Nanum Gothic',
  },
});

class LayerScreen extends React.PureComponent {
  componentDidMount() {
    console.log('component is mounted');
  }
  componentWillUnmount() {
    console.log('component did unmounted.');
  }
  render() {
    const ContentList = mockData.map((data) => {
      return (
        <TouchableHighlight
          activeOpacity={0.8}
          underlayColor="#DBE8F1"
          onPress={() => {
            console.log(`${data.id} content is pressed`);
          }}
          style={styles.container}
          key={data.id}
        >
          <React.Fragment>
            <Text>{data.title}</Text>
          </React.Fragment>
        </TouchableHighlight>
      );
    });
    return <ScrollView>{ContentList}</ScrollView>;
  }
}

const LayerStack = createStackNavigator();

export default function LayerStackScreen() {
  return (
    <LayerStack.Navigator>
      <LayerStack.Screen
        name="Layer"
        component={LayerScreen}
        options={{
          headerTitle: 'MOOC',
          headerStyle: {
            backgroundColor: '#2399E7',
          },
          headerTintColor: '#fff',
          headerTitleStyle: {
            fontFamily: 'Nanum Gothic',
            fontWeight: 'bold',
          },
        }}
      />
    </LayerStack.Navigator>
  );
}
