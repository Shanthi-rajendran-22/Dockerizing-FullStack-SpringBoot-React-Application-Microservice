import './App.css';
import React, { Component } from 'react';
import axios from 'axios';
class App extends Component {
  constructor(props) {
    super(props);
    this.state = { data: null };
   }

    componentDidMount() {
       
        axios.get('http://localhost:8082/hello-world')
            .then(res => {
                console.log(res);
                this.setState({ data: res.data.message});
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    displayData () { return <h1>{this.state.data}</h1>}

    displayLoading() { return <p>Fetching data from Server</p> }

  render(){
    return (
      <div className="App">
        <div className="App-body">
        {this.state.data == null ? this.displayLoading() : this.displayData() }
        </div>
      </div>
    );
  }
}

export default App;
