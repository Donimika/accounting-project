import React, {useEffect, useState} from "react";
import {apiDelete, apiGet} from "../utils/api";
import InvoiceTable from "./InvoiceTable";
import StatsButton from "../components/StatsButton";
import InvoiceFilter from "./InvoiceFilter";
import FlashMessage from '../components/FlashMessage';

const InvoiceIndex = () => {
    const [invoices, setInvoices] = useState([]);
    const [buyerListState, setBuyerList] = useState([]);
    const [sellerListState, setSellerList] = useState([]);
    const [productListState, setProductList] = useState([]);
    const [filterState, setFilter] = useState({
    buyerId: undefined,
    sellerId: undefined,
    product: undefined,
    minPrice: undefined,
    maxPrice: undefined,
    limit: undefined,
    });
    const [successState, setSuccessState] = useState(false);
    const [productValue, setProductValue] = useState();


    const deleteInvoice = async (id) => {
        try {
            await apiDelete("/api/invoices/" + id);
            setSuccessState(true);
        } catch (error) {
            console.log(error.message);
            alert(error.message)
        }
        setInvoices(invoices.filter((item) => item._id !== id));
    
    };

    
    useEffect(() => {
        if (successState) {
            const timer = setTimeout(() => {
                setSuccessState(false); 
            }, 2000);

            return () => clearTimeout(timer);
        }
    }, [successState]);


    useEffect(() => {
        apiGet("/api/invoices").then((data) => {
            setInvoices(data);
            const products = data.map((invoice) => ({
                name: invoice.product,
                value: invoice.product
                
            }));
            setProductList(products);
        });
    }, []);


    useEffect(() => {
        apiGet('/api/persons').then((data) => setBuyerList(data));
        apiGet('/api/persons').then((data) => setSellerList(data));
    }, []);


    const handleChange = (e) => {
        if (e.target.value === "false" || e.target.value === "true" || e.target.value === '') {
            setFilter(prevState => {
                return {...prevState, [e.target.name]: undefined}
            });
        } else {
            console.log(e);
            setFilter(prevState => {
                return { ...prevState, [e.target.name]: e.target.value}
            });
        }
    };


    const handleProductChange = (e) => {
          setFilter(prevState => {
            return { ...prevState, product: e.value }; 
          });

    };

    
    const handleSubmit = async (e) => {
        e.preventDefault();
        const params = filterState;
    
        const data = await apiGet("/api/invoices", params);
        setInvoices(data);
        
        setFilter(prevState => ({ ...prevState, product: undefined }));
        setProductValue("undefined");

    };

    {/*
    searchValue: Hodnota, kterou uživatel zadal do pole vyhledávání.
    callback: Funkce, která se zavolá poté, co jsou možnosti načteny, pak jsou předány AsyncSelectu a ten je dále zpracovává.
    */}
    const loadOptions = (searchValue, callback) => {
        const filteredProducts = productListState.filter(product => 
               product.name.toLowerCase().includes(searchValue.toLowerCase()));
           console.log('loadOptions', searchValue, filteredProducts); 
           callback(filteredProducts);
   }

    return (
        <div>
            { successState && (
                <FlashMessage
                    theme={"success"}
                    text={"Odstranění faktury proběhlo úspěšně."}
                />
            )}
            <div className="row pt-4">
                <div className="col">
                    <h1>Seznam faktur </h1>
                </div>
                <div className="col">
                    <StatsButton to={"/invoices/statistics"} label="Přehled statistik pro faktury" />   
                </div>
            </div>
            <hr />
            <InvoiceFilter 
                handleChange={handleChange}
                handleProductChange={handleProductChange}
                handleSubmit={handleSubmit}
                buyerList={buyerListState}
                sellerList={sellerListState}
                productList={productListState}
                filter={filterState}
                value={productValue}
                loadOptions={loadOptions}
                confirm="Vyfiltruj faktury"
            />
            <InvoiceTable
                deleteInvoice={deleteInvoice}
                items={invoices}
                label="Počet faktur:"
            />
        </div>
    );
};
export default InvoiceIndex;