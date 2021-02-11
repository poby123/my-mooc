import React from 'react';
import { Text, View, TouchableHighlight, ScrollView, StyleSheet } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import HeaderOptions from '../HeaderOptions';
import ListComponent from '../components/ListComponent';
import BoardComponent from '../components/BoardComponent';

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
    const { navigation } = this.props;
    const ContentList = mockData.map((data) => {
      return (
        <TouchableHighlight
          activeOpacity={0.8}
          underlayColor="#DBE8F1"
          onPress={() => {
            navigation.push('BoardList', data);
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
    <LayerStack.Navigator initialRouteName={'Layer'}>
      <LayerStack.Screen name="Layer" component={LayerScreen} options={HeaderOptions} />
      <LayerStack.Screen name="BoardList" component={ListComponent} options={HeaderOptions} />
      <LayerStack.Screen name="Board" component={BoardComponent} options={HeaderOptions} />
    </LayerStack.Navigator>
  );
}
