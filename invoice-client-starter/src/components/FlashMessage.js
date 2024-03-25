import React from "react";

export function FlashMessage({theme, text}) {
    return <div className={"alert alert-" + theme} style={{ position: "fixed", top: 0, left: 0, right: 0, zIndex: 999}}>{text}</div>;
}

export default FlashMessage;
