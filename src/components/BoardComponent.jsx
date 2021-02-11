import React from 'react';
import { View, ScrollView, StyleSheet } from 'react-native';
import Theme from '../Theme';
import WriterComponent from './WriterComponent';
import ContentComponent from './ContentComponent';
import FavoriteComponent from './FavoriteComponent';

const styles = StyleSheet.create(Theme);

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
          <WriterComponent image={comment.writer.image} name={comment.writer.name} date={comment.date} />
          <ContentComponent content={comment.content} />
        </View>
      );
    });
    return (
      <ScrollView style={styles.container} key={this.mockData.id}>
        <WriterComponent image={writer.image} name={writer.name} date={content.date} />
        <ContentComponent image={content.image} content={content.content} />
        <FavoriteComponent favoriteNumber={content.favorite} myFavorite={content.myFavorite} />
        <View>{Comments}</View>
      </ScrollView>
    );
  }
}
