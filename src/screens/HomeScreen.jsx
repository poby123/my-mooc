import React from 'react';
import { Text, View, ScrollView, Image, StyleSheet } from 'react-native';
import { IconButton } from '@material-ui/core';
import FavoriteIcon from '@material-ui/icons/Favorite';
import FavoriteBorderIcon from '@material-ui/icons/FavoriteBorder';
import { createStackNavigator } from '@react-navigation/stack';

const mockData = [
  {
    writer: {
      name: '뽀로로',
      image: 'https://pds.joins.com/news/component/moneytoday/201110/04/2011100410252859481_1.jpg',
    },
    content: {
      id: '1',
      date: '2021.02.10 12:16',
      content:
        'To be able to interact with the screen component, we need to use navigation.setOptions to define our button instead of the options prop. By using navigation.setOptions inside the screen component, we get access to screens props, state, context etc.',
      files: ['file link1, file link2'],
      myLove: true,
      love: 9,
    },
  },
];

const styles = StyleSheet.create({
  container: {
    marginTop: '0.5em',
    marginLeft: '0.5em',
    marginBottom: '0.5em',
    marginRight: '0.5em',
    fontFamily: 'Nanum Gothic',
  },
  writerContainer: {
    flexDirection: 'row',
  },
  writerSubContainer: {
    flexDirection: 'column',
    justifyContent: 'center',
  },
  profileImage: {
    width: '50px',
    height: '50px',
    borderRadius: '50%',
    marginRight: '1em',
  },
  contentContainer: {
    flexDirection: 'column',
  },
  contentLoveContainer: {
    alignItems: 'center',
    flexDirection: 'row',
    color: 'red',
  },
  loveButton: {
    width: '50px',
  },
});

class HomeScreen extends React.PureComponent {
  componentDidMount() {
    console.log('component is mounted');
  }
  componentWillUnmount() {
    console.log('component did unmounted.');
  }
  render() {
    const ContentList = mockData.map((data) => {
      const writer = data.writer;
      const content = data.content;
      return (
        <View style={styles.container} key={content.id}>
          <View style={styles.writerContainer}>
            <Image style={styles.profileImage} source={{ uri: writer.image }} />
            <View style={styles.writerSubContainer}>
              <Text>{writer.name}</Text>
              <Text>{content.date}</Text>
            </View>
          </View>
          <View style={styles.contentContainer}>
            <Text style={{ fontFamily: 'inherit' }}>{content.content}</Text>
            <View style={styles.contentLoveContainer}>
              <IconButton color="inherit" onClick={() => console.log('pressed')}>
                {content.myLove ? <FavoriteIcon /> : <FavoriteBorderIcon />}
              </IconButton>
              <Text styles={{ color: 'red', width: '20px' }}>{content.love}</Text>
            </View>
          </View>
        </View>
      );
    });
    return <ScrollView>{ContentList}</ScrollView>;
  }
}

const HomeStack = createStackNavigator();

export default function HomeStackScreen() {
  return (
    <HomeStack.Navigator>
      <HomeStack.Screen
        name="Home"
        component={HomeScreen}
        options={{
          title: 'MANMIN YOUTH',
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
    </HomeStack.Navigator>
  );
}
