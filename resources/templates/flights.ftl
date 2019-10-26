<#import "common/bootstrap.ftl" as b>

<@b.page>
    <#if flights?? && (flights?size > 0)>

         <table class="table table-striped">
             <thead>
                 <tr>
                     <th>Date</th>
                     <th>Origin</th>
                     <th>Destination</th>
                     <th>Booking Reference</th>
                     <th>Departing time</th>
                     <th>Arrival time</th>
                     <th>Airline</th>
                     <th>People</th>
                     <th>Paid price</th>

                 </tr>
             </thead>
             <tbody>
                 <#list flights as flight>
                     <tr>
                         <td style="vertical-align:middle"><h4>${flight.departingDate}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.origin}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.destination}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.bookingReference}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.departingTime}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.arrivalTime}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.airline}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.people}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.price}</h4></td>
                         <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                            <form method="post" action="/flights">
                                <input type="hidden" name="date" value="${date?c}">
                                <input type="hidden" name="code" value="${code}">
                                <input type="hidden" name="id" value="${flight.bookingReference}">
                                <input type="hidden" name="action" value="delete">
                                <input type="image" src="/static/delete.png" width="24" height="24" border="0 alt="Delete" />
                            </form>
                         </td>
                     </tr>
                 </#list>
             </tbody>
         </table>
    </#if>

<h3>Insert new flight:</h3>
 <div class="panel-body">
    <form method="post" action="/flights">
        <input type="hidden" name="date" value="${date?c}">
        <input type="hidden" name="code" value="${code}">
        <input type="hidden" name="action" value="add">
        Booking reference </br>
        <input type="text" name="bookingReference" required/>
        </br> </br>
        Origin </br>
        <input type="text" name="origin" required/>
        </br> </br>
        Departing date & time </br>
        <input type="datetime-local" name="departing_date" value="2020-01-01T10:00" required>
        </br> </br>
        Destination </br>
        <input type="text" name="destination" required/>
        </br> </br>
        Arrival date & time </br>
        <input type="datetime-local" name="arrival_date" value="2020-01-01T10:00" required/>
        </br> </br>
        Airline: </br>
        <input type="text" name="airline" required/>
        </br> </br>
        People: </br>
        <input type="text" name="people" required/>
        </br> </br>
        Price: </br>
        <input type="text" name="price" required/>
        </br> </br>
        </br>
        <input type="submit" value="Submit" />
    </form>
    </div>

</@b.page>