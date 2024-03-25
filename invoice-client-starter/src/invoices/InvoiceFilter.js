import React from 'react';
import InputSelect from '../components/InputSelect';
import InputField from '../components/InputField';
import AsyncSelec from '../components/AsyncSelec';

const InvoiceFilter = (props) => {

    const handleChange = (e) => {
        props.handleChange(e);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        props.handleSubmit(e);
    };

    const handleProductChange = (e) => {
        props.handleProductChange(e);
    };

    const filter = props.filter;

   
    return (
    <form onSubmit={handleSubmit} className='pt-1 pb-4'>
        <div className="row">
            <div className="col">
                <InputSelect
                    name="buyerID"
                    items={props.buyerList}
                    handleChange={handleChange}
                    label="Kupující"
                    prompt="nevybrán"
                    value={filter.buyerId}
                />
            </div>
            <div className="col">
                <InputSelect
                    name="sellerID"
                    items={props.sellerList}
                    handleChange={handleChange}
                    label="Prodávající"
                    prompt="nevybrán"
                    value={filter.sellerId}
                />
            </div>
        </div>

        <div className="row">
            <div className="col">
                <InputField
                    type="number"
                    min="0"
                    name="minPrice"
                    handleChange={handleChange}
                    label="Minimální částka"
                    prompt="neuveden"
                    value={filter.minPrice ? filter.minPrice : ''}
                />
            </div>

            <div className="col">
                <InputField
                    type="number"
                    min="0"
                    name="maxPrice"
                    handleChange={handleChange}
                    label="Maximální částka"
                    prompt="neuveden"
                    value={filter.maxPrice ? filter.maxPrice : ''}
                />
            </div>
        </div>

        <div className="row">
            <div className="col">
                <AsyncSelec
                    name="product"
                    loadOptions={props.loadOptions}
                    handleChange={handleProductChange}
                    label="Produkt"
                    prompt="nevybráno"
                    value={props.value}
                    
                />
            </div>

            <div className="col">
                <InputField
                    type="number"
                    min="1"
                    name="limit"
                    handleChange={handleChange}
                    label="Limit počtu faktur"
                    prompt="neuveden"
                    value={filter.limit ? filter.limit : ''}
                />
            </div>
        </div>
        

        <div className="row pt-2">
            <div className="col">
                <input
                    type="submit"
                    className="btn btn-secondary float-right mt-2"
                    value={props.confirm}
                />
            </div>
        </div>
    </form>
    );
};

export default InvoiceFilter;