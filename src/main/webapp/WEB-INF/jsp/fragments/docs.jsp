<%@ include file="../fragments/taglib.jspf" %>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

<div id="docWindow" class="modal fade" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="background-color:#ffffff;">
        <!-- Header-->
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
                <span aria-hidden="true">&times;</span>
                <span class="sr-only">Close</span>
            </button>
            <h4 class="modal-title">CTPay Office guide docs</h4>
        </div>
        <div class="modal-content" style="border-radius: 1px;">
            <c:choose>
                <c:when test="false">
                    <ul>
                        <li><spring:message code='DOC_ERROR_TROUBLESHOOTING_GUIDE_WEB_SERVICES'/></li>
                        <li><spring:message code='DOC_API_REFERENCE_GUIDE'/></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul>
                        <li><spring:message code='DOC_QC_DELIVERABLE'/></li>
                        <li><spring:message code='DOC_GESTION_GUIDE'/></li>
                        <li><spring:message code='DOC_SAAQ_WEB_SERVICES'/></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
        <!-- Footer -->
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
    </div>
</div>
