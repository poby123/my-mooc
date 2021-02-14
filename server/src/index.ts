import App from './App';

const app = new App().application;

app.listen(3001, () => {
    console.log('Server listening on port 3001');
});