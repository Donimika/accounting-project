import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import InputSelect from "../components/InputSelect";
import {apiGet, apiPost, apiPut} from "../utils/api";
import InputField from "../components/InputField";
import FlashMessage from "../components/FlashMessage";

const InvoiceForm = () => {
    const navigate = useNavigate();
    const {id} = useParams();

    const [invoice, setInvoice] = useState({
        invoiceNumber: "",
        issued: "",
        dueDate: "",
        product: "",
        price: "",
        vat: "",
        note: "",
        buyer: {_id:-1},
        seller: {_id:-1}
    });
    const [sentState, setSent] = useState(false);
    const [successState, setSuccess] = useState(false);
    const [errorState, setError] = useState(null);
    const [persons, setPersons] = useState([]);

    // Načtení faktur
    useEffect(() => {
        if (id) {
            // Načtení dat z API faktur
            apiGet("/api/invoices/" + id)
                .then((data) => setInvoice(data))
                .catch((error) => console.error('Chyba při načítání dat z API faktur: ', error));
        }
    }, [id]);

        // Načtení osob
        useEffect(() => {
            const fetchData = async () => {
                try {
                    const response = await apiGet("/api/persons");
                    // Nastavení stavu s načtenými jmény osob
                    setPersons(response);
                } catch (error) {
                    console.error('Chyba při načítání dat z API osob: ', error);
                }
            };
    
            // Zavolání funkce pro načtení dat z API osob
            fetchData();
        }, []);
    

    const handleSubmit = (e) => {
        e.preventDefault();

        (id ? apiPut("/api/invoices/" + id, invoice) : apiPost("/api/invoices", invoice))
            .then((data) => {
                setSent(true);
                setSuccess(true);
                
            })
            .catch((error) => {
                console.log(error.message);
                setError(error.message);
                setSent(true);
                setSuccess(false);
            });
    };

    useEffect(() => {
        if (sentState && successState) {
            const timer = setTimeout(() => {
                navigate("/invoices");
            }, 2000);

            return () => clearTimeout(timer);
        }
    }, [sentState, successState]);

    const sent = sentState;
    const success = successState;



    return (
        <div>
            <h1>{id ? "Upravit" : "Vytvořit"} fakturu </h1>
            <hr/>
            {errorState ? (
                <div className="alert alert-danger">{errorState}</div>
            ) : null}
            {sent && success ? (
                <FlashMessage
                    theme={"success"}
                    text={"Uložení faktury proběhlo úspěšně."}
                />
            ): null}
            <form onSubmit={handleSubmit}>
                <InputField
                    required={true}
                    type="number"
                    name="invoiceNumber"
                    label="Číslo faktury"
                    prompt="Zadejte číslo faktury"
                    value={invoice.invoiceNumber}
                    handleChange={(e) => {
                        setInvoice({...invoice, invoiceNumber: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="date"
                    name="issued"
                    label="Vystaveno"
                    prompt="Zadejte datum vystavení faktury"
                    value={invoice.issued}
                    handleChange={(e) => {
                        setInvoice({...invoice, issued: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="date"
                    name="dueDate"
                    label="Splatnost"
                    prompt="Zadejte datum splatnosti"
                    value={invoice.dueDate}
                    handleChange={(e) => {
                        setInvoice({...invoice, dueDate: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="text"
                    name="product"
                    min="3"
                    label="Název produktu"
                    prompt="Zadejte název produktu"
                    value={invoice.product}
                    handleChange={(e) => {
                        setInvoice({...invoice, product: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="number"
                    name="price"
                    label="Cena"
                    prompt="Zadejte cenu produktu"
                    value={invoice.price}
                    handleChange={(e) => {
                        setInvoice({...invoice, price: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="number"
                    name="tax"
                    label="DPH"
                    prompt="Zadejte DPH"
                    value={invoice.vat}
                    handleChange={(e) => {
                        setInvoice({...invoice, vat: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="text"
                    name="note"
                    min="2"
                    label="Poznámka"
                    prompt="Zadejte poznámku"
                    value={invoice.note}
                    handleChange={(e) => {
                        setInvoice({...invoice, note: e.target.value});
                    }}
                />
                
                <InputSelect 
                items={persons} 
                required={true}
                value={invoice.buyer._id} 
                label="Kupující"
                name="buyer"
                handleChange={(e) => {
                    setInvoice({...invoice, buyer: {_id: e.target.value}});
                }}
                />

                <InputSelect 
                items={persons} 
                required={true}
                value={invoice.seller._id} 
                label="Prodávající"
                name="seller"
                handleChange={(e) => {
                    setInvoice({...invoice, seller: {_id: e.target.value}});
                }}
                />
            
                <input type="submit" className="btn btn-primary" value="Uložit"/>
            </form>
        </div>
    );
};

export default InvoiceForm;