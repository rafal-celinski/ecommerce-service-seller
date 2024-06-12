// App.js
import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './Home';
import Auction from './Auction';
import AddAuction from './AddAuction';
import Orders from './Orders';

function App() {
    return (
            <BrowserRouter>
                <Routes>
                    <Route path="/" element=<Home /> />
                    <Route path="/auction/:auctionId" element=<Auction /> />
                    <Route path="/add" element=<AddAuction /> />
                    <Route path="/orders" element=<Orders /> />
                </Routes>
            </BrowserRouter>
    );
}

export default App;
