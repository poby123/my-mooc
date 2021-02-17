const styles = {
  container: {
    backgroundColor: 'white',
    marginTop: '0.3em',
    marginLeft: '0.5em',
    marginBottom: '0.5em',
    marginRight: '0.5em',
    fontFamily: 'Nanum Gothic',
  },
  writerContainer: {
    marginTop: '1em',
    paddingLeft: '0.5em',
    paddingRight: '0.5em',
    flexDirection: 'row',
    justifyContent: 'space-between',
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
  modal: {
    alignItems: 'center',
    padding: '1em',
    width: '100%',
    minHeight: '60vh',
    position: 'absolute',
    bottom: 0,
    backgroundColor: '#FFFFFF',
    border: '1px solid gray',
  },

  contentContainer: {
    flexDirection: 'column',
    paddingTop: '0.5em',
    paddingRight: '0.5em',
    paddingBottom: '0.5em',
    paddingLeft: '0.5em',
  },
  contentContent: {
    fontFamily: 'inherit',
    marginTop: '0.5em',
  },
  contentLoveContainer: {
    alignItems: 'center',
    flexDirection: 'row',
    color: 'red',
  },
  loveButton: {
    width: '50px',
  },
  commentInputContainer: {
    backgroundColor: '#FFFFFF',
    width: '100%',
    flexDirection: 'row',
    alignItems: 'center',
  },
  commentInput: {
    borderColor: 'gray',
    borderWidth: 1,
    height: 40,
    paddingLeft: '1em',
    paddingRight: '1em',
    flex: 8,
  },
  commentPostButton: {
    border: '1px solid red',
  },

  /* */
  postContainer: {
    marginTop: '0.3em',
    marginLeft: '0.5em',
    marginBottom: '0.5em',
    marginRight: '0.5em',
    fontFamily: 'Nanum Gothic',
    flexDirection: 'column',
  },
  postSubContainer: {
    borderRadius: '5px',
    backgroundColor: '#FFFFFF',
    flexDirection: 'row',
    alignItems: 'center',
    border: '1px solid gray',
    padding: '1em',
  },
  postSelect: {
    color: '#FF0000',
  },
  postLabel: {
    flex: 5,
  },
  postInput: {
    borderRadius: '5px',
    border: '1px solid gray',
    padding: '1em',
    backgroundColor: '#FFFFFF',
    height: '50vh',
    flexDirection: 'row',
    alignItems: 'center',
  },
  postButton: {},
};

export default styles;