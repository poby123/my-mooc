import React from 'react';
import { Text, View, ScrollView, Image, StyleSheet } from 'react-native';
import { IconButton } from '@material-ui/core';
import FavoriteIcon from '@material-ui/icons/Favorite';
import FavoriteBorderIcon from '@material-ui/icons/FavoriteBorder';

const styles = StyleSheet.create({
  container: {
    backgroundColor: 'white',
    marginTop: '0.3em',
    marginLeft: '0.5em',
    marginBottom: '0.3em',
    marginRight: '0.5em',
    paddingTop: '1em',
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
  commentContainer: {
    flexDirection: 'row',
  },
});

export default class BoardComponent extends React.PureComponent {
  constructor(props) {
    super(props);
    this.props = props;
    this.mockData = props.route.params;
  }
  
  render() {
    const writer = this.mockData.writer;
    const content = this.mockData.content;
    const Comments = this.mockData.comment.map((comment) => {
      return (
        <View key={comment.id}>
          <View style={styles.commentContainer}>
            <Image style={styles.profileImage} source={{ uri: comment.writer.image }} />
            <View style={styles.writerSubContainer}>
              <Text>{comment.writer.name}</Text>
              <Text>{comment.date}</Text>
            </View>
          </View>
          <View style={styles.contentContainer}>
            <Text style={{ fontFamily: 'inherit' }}>{comment.content}</Text>
          </View>
        </View>
      );
    });
    return (
      <ScrollView style={styles.container} key={this.mockData.id}>
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
        <View>{Comments}</View>
      </ScrollView>
    );
  }
}
