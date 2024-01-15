// import logo from './logo.svg';
import './App.css';
import { HoldemGame } from './holdem-game/HoldemGame';

function App() {
  console.log(`Application running in ${process.env.NODE_ENV} mode.`);
  return (
    <div className="App">
      <header className="App-header">
      </header>
      <HoldemGame />
    </div>
  );
}

export default App;
