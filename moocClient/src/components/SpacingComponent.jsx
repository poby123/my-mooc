import React from 'react';
import { View } from 'react-native';

export default function SpacingComponent({ spacing = 2}) {
  return <View style={{ minHeight: `${spacing * 0.5}em` }}></View>;
}
