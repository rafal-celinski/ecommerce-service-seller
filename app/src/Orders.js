import React, {useEffect, useState} from 'react';
import './Orders.css';
import {Link} from "react-router-dom";
import Header from "./Header";

function Orders() {
    const [orders, setOrders] = useState(null);

    function fetchOrders() {
        fetch("http://localhost:8000/history.json")
            .then(response => response.json())
            .then(data => setOrders(data));
    }

    useEffect(fetchOrders, []);

    function formatDate(stringDate) {
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        const date = new Date(stringDate);
        return date.toLocaleDateString('pl-PL', options);
    }

    function formatState(state) {
        if(state === "pending"){
            return "Oczekuje na realizację";
        }
        if(state === "delivered"){
            return "Dostarczono";
        }
        if(state === "sent"){
            return "Wysłano";
        }
        if(state === "cancelled"){
            return "Anulowano";
        }
    }

    function changeState(auctionId, newState) {
        fetch("http://localhost:8000/path_state/{auctionID}", {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({state: newState}),
        });
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
                            <img src={item.imageUrl[0]} alt="" />
                        </div>
                        <div className="ProductInfo">
                            <Link className="ProductTitle" to={`/auction/${item.id}`}>
                                {item.title}
                            </Link>
                            <div className="ProductQuantity">
                               Ilość: {item.quantity}
                            </div>
                            <div className="ProductDateAndState">
                                <span>{formatDate(item.date)}</span>
                                <span className="ProductState">{formatState(item.state)}</span>
                            </div>
                            <div className="ChangeState">
                                {item.state === "pending" && (
                                    <>
                                        <button onClick={() => changeState(item.id, "sent")}>Wysłana</button>
                                        <button onClick={() => changeState(item.id, "cancelled")}>Anuluj</button>
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