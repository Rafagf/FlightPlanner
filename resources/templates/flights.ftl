<#import "common/bootstrap.ftl" as b>

<@b.page>
    <#if phrases?? && (phrases?size > 0)>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Emoji</th>
                    <th>Phrase</th>
                </tr>
            </thead>
            <tbody>
            <#list phrases as phrase>
                <tr>
                    <td style="vertical-align:middle"><h3>${phrase.emoji}</h3></td>
                    <td style="vertical-align:middle"><h3>${phrase.phrase}</h3></td>
                    <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                        <form method="post" action="/phrases">
                            <input type="hidden" name="date" value="${date?c}">
                            <input type="hidden" name="code" value="${code}">
                            <input type="hidden" name="id" value="${phrase.id}">
                            <input type="hidden" name="action" value="delete">
                            <input type="image" src="/static/delete.png" width="24" height="24" border="0 alt="Delete" />
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
    <div class="panel-body">
    <form method="post" action="/phrases">
        <input type="hidden" name="date" value="${date?c}">
        <input type="hidden" name="code" value="${code}">
        <input type="hidden" name="action" value="add">
        Emoji:<br>
        <input type="text" name="emoji" /><br>
        Phrase:<br>
        <input type="text" name="phrase" /><br>
        <input type="submit" value="Submit" />
    </form>
    </div>
</@b.page>