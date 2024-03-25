import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

import {apiGet} from "../utils/api";

const InvoiceDetail = () => {
    const {id} = useParams();
    const [invoice, setInvoice] = useState({});
    const [buyer, setBuyer] = useState({});
    const [seller, setSeller] = useState({});
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        apiGet("/api/invoices/" + id).then((data) => {setInvoice(data);
        const buyer = data.buyer;
        setBuyer(buyer);
        const seller = data.seller;
        setSeller(seller);
        setLoading(false);
        });
    }, [id]);

    return (
        <>
            {loading ? (
                <>
                    <h5>Načítám.....</h5>
                </>
            ): (
                <>  
                    <div>
                        <h1>Detail faktury</h1>
                        <hr/>
                        <h3>Faktura č. {invoice.invoiceNumber}</h3>
                        <br/>
                        <p>
                            <strong>Vystaveno:</strong>
                            <br/>
                            {invoice.issued}
                        </p>
                        <p>
                            <strong>Datum splatnosti:</strong>
                            <br/>
                            {invoice.dueDate}
                        </p>
                        <p>
                            <strong>Produkt:</strong>
                            <br/>
                            {invoice.product}
                        </p>
                        <p>
                            <strong>Price:</strong>
                            <br/>
                            {invoice.price} Kč
                        </p>
                        <p>
                            <strong>DPH:</strong>
                            <br/>
                            {invoice.vat} %
                        </p>
                        <p>
                            <strong>Poznámka:</strong>
                            <br/>
                            {invoice.note}
                        </p>
                        <p>
                            <strong>Kupující:</strong>
                            <br />
                            {buyer.name} 
                        </p>
                        <p>
                            <strong>Prodávající:</strong>
                            <br />
                            {seller.name} 
                        </p>
                    </div>
                </>
            )}
            
        </>
    );
};

export default InvoiceDetail;