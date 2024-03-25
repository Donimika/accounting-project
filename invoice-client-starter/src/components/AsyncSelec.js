import React from "react";
import AsyncSelect from "react-select/async";


export function AsyncSelec(props) {
  const multiple = props.multiple;
  const required = props.required || false;
  const emptySelected = multiple ? props.value?.length === 0 : !props.value;
  const objectItems = !props.enum;


  return (
    <div className="form-group">
      <label>{props.label}:</label>

      {/* 
       *   getOptionLabel - určuje, jak se zobrazí každá možnost v seznamu 
      */}

      <AsyncSelect
        required={required}
        className="browser-default"
        loadOptions={props.loadOptions}
        isMulti={multiple}
        name={props.name}
        onChange={props.handleChange}
        value={props.value}
        placeholder="hledej.."
        
        getOptionLabel={(option) => objectItems ? option.value : option}
        
      >

          {required ? (
          <option disabled value={emptySelected}>
            {props.prompt}
          </option>
        ) : (
          <option key={0} value={emptySelected}>
            ({props.prompt})
          </option>
        )}


      </AsyncSelect>
    </div>
  );
}

export default AsyncSelec;