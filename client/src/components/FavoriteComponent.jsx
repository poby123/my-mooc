import React from 'react';
import { Text, View, StyleSheet } from 'react-native';
import { IconButton } from '@material-ui/core';
import FavoriteIcon from '@material-ui/icons/Favorite';
import FavoriteBorderIcon from '@material-ui/icons/FavoriteBorder';
import Theme from '../Theme';

const styles = StyleSheet.create(Theme);

export default class FavoriteComponent extends React.PureComponent{
  constructor(props) {
    super(props);
    this.props = props;
  }
  render() {
    const { myFavorite, favoriteNumber } = this.props;
    return (
      <View style={styles.contentLoveContainer}>
        <IconButton color="inherit" onClick={() => console.log('pressed')}>
          {myFavorite ? <FavoriteIcon /> : <FavoriteBorderIcon />}
        </IconButton>
        <Text styles={{ color: 'red', width: '20px' }}>{favoriteNumber}</Text>
      </View>
    );
  }
}
