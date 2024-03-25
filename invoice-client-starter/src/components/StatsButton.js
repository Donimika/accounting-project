import React from 'react';

const StatsButton = ({ label, to }) => { // prop to pro určení cílového odkazu
    const buttonStyle = {
        padding: "0.625rem 1.25rem",
        fontSize: "1.25rem",
        borderRadius: "0.5rem",
        marginBottom: "0.625rem",
        backgroundColor: "#5bc0de",
        color: "#000000",
        border: "1px solid #808080",
        cursor: "pointer",
        transition: "background-color 0.3s, color 0.3s"
    };

    return (
        <div style={{float: "right" }}>
            <button
                style={buttonStyle}
                onMouseOver={(e) => e.target.style.cssText += 'background-color: #0084BD; color: #FFFFFF;'}
                onMouseOut={(e) => e.target.style.cssText += 'background-color: #5bc0de; color: #000000;'}
                onClick={() => window.location.href = to} 
            >
                {label}
            </button>
        </div>
    );
};

export default StatsButton;