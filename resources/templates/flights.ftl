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
                         <td style="vertical-align:middle"><h4>${flight.date}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.origin}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.destination}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.bookingReference}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.departingTime}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.arrivalTime}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.airline}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.people}</h4></td>
                         <td style="vertical-align:middle"><h4>${flight.price}</h4></td>
                         <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                         </td>
                     </tr>
                 </#list>
             </tbody>
         </table>
    </#if>

<h3>Insert new flight:</h3>
 <div class="panel-body">
    <form method="post" action="/flights">

        Flight date:
        <input type="date" name="date" required>
        </br> </br>
        Origin:
        <input type="text" name="origin" required/>
        </br> </br>
        Departing time:
        <input type="time" name="departing-time" required>
        </br> </br>
        Destination:
        <input type="text" name="destination" required/>
        </br> </br>
        Arrival time:
        <input type="time" name="arrival-time">
        </br> </br>
        Booking reference:
        <input type="text" name="booking" />
        </br> </br>
        Airline:
        <input type="text" name="airline" /><br>
        </br> </br>
        People:
        <input type="text" name="people" /><br>
        </br> </br>
        Price:
        <input type="text" name="price" /><br>
        </br> </br>
        <input type="submit" value="Submit" />
    </form>
    </div>

</@b.page>