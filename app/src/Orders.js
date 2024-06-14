import React, {useEffect, useState} from 'react';
import './Orders.css';
import {Link} from "react-router-dom";
import Header from "./Header";

function Orders() {
    const [orders, setOrders] = useState(null);

    function fetchOrders() {
        fetch(process.env.REACT_APP_API_URL + "/products/all")
            .then(response => response.json())
            .then(data => setOrders(data.content));
    }

    useEffect(fetchOrders, []);

    function formatDate(stringDate) {
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        const date = new Date(stringDate);
        return date.toLocaleDateString('pl-PL', options);
    }

    function formatState(state) {
        if(state === "SOLD"){
            return "Oczekuje na realizację";
        }
        if(state === "delivered"){
            return "Dostarczono";
        }
        if(state === "SENT"){
            return "Wysłano";
        }
        if(state === "cancelled"){
            return "Anulowano";
        }
    }

    function changeState(auctionId, newState) {
        fetch(process.env.REACT_APP_API_URL + "/send/" + auctionId, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            }
        })
    }

    if (!orders) {
        return <div>Ładowanie...</div>;
    }

    if(orders.length ===0){
        return <div>Brak zamówień</div>;
    }

    return (
        <div>
            <Header />
            <div className="Orders">
            <div className="OrdersHeader">Twoje zamówienia</div>
            <div className="Products">
                {orders.map((item) => (
                    <div className="Product" key={item.id}>
                        <div className="ProductImage">
                            <img src={item.imageUrls[0]} alt="" />
                        </div>
                        <div className="ProductInfo">
                            <Link className="ProductTitle" to={`/auction/${item.id}`}>
                                {item.title}
                            </Link>
                            <div className="ProductDateAndState">
                                <span>{formatDate(item.date)}</span>
                                <span className="ProductState">{formatState(item.status)}</span>
                            </div>
                            <div className="ChangeState">
                                {item.status === "SOLD" && (
                                    <>
                                        <button onClick={() => changeState(item.id, "sent")}>Wysłana</button>
                                    </>
                                )}
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
        </div>
    );

}

export default Orders;