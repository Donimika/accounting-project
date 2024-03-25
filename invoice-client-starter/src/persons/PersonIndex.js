/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */

import React, {useEffect, useState} from "react";

import {apiDelete, apiGet} from "../utils/api";

import PersonTable from "./PersonTable";

import StatsButton from "../components/StatsButton";

import FlashMessage from "../components/FlashMessage";

const PersonIndex = () => {
    const [persons, setPersons] = useState([]);
    const [successState, setSuccessState] = useState(false);


    const deletePerson = async (id) => {
        try {
            await apiDelete("/api/persons/" + id);
        } catch (error) {
            console.log(error.message);
            alert(error.message)
        }
        setPersons(persons.filter((item) => item._id !== id));
        setSuccessState(true);
    };


    useEffect(() => {
        apiGet("/api/persons").then((data) => setPersons(data));
    }, []);


    useEffect(() => {
        if (successState) {
            const timer = setTimeout(() => {
                setSuccessState(false); 
            }, 2000);

            return () => clearTimeout(timer);
        }
    }, [successState]);


    return (
        <div>
            { successState && (
                <FlashMessage
                    theme={"success"}
                    text={"Odstranění osoby proběhlo úspěšně."}
                />
            )}
            <div className="row pt-4">
                <div className="col">
                    <h1>Seznam osob</h1>
                </div>
                <div className="col">
                    <StatsButton to={"/persons/statistics"} label="Přehled statistik pro jednotlivé osoby" />
                </div>
            </div>
            <PersonTable
                deletePerson={deletePerson}
                items={persons}
                label="Počet osob:"
            />
        </div>
    );
};
export default PersonIndex;
