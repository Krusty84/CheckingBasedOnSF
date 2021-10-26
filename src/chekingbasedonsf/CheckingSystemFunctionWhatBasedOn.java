package chekingbasedonsf;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class CheckingSystemFunctionWhatBasedOn extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		//�������� ��������� ������������ ���� EObject, �� �������� ��������� ������� CAPELLA
		EObject eObject = ctx.getTarget();
		//��������� ������ ���� ���� �������, ����������� �� EObject
		//��� ��������� ������� SystemFunction
		if (eObject instanceof SystemFunction) {
			//�������� ����
			SystemFunction currentSysFunc = (SystemFunction)eObject;
			//
			int iWhatIsRealized;
			//������� ���-�� ������ ����� ��������� �������� � ������������� ����������
			iWhatIsRealized = currentSysFunc.getRealizedOperationalActivities().size();
			//���� ����� ������ - ���, �������� �������� ��� ����������
			if (iWhatIsRealized<=0) {
				if (currentSysFunc instanceof AbstractNamedElement) {
					AbstractNamedElement currentNamedElement = (AbstractNamedElement)currentSysFunc;
					//���� �������� ������� ������ � ���������� ���������� ������� ����� ���
					return ctx.createFailureStatus(new Object [] {currentSysFunc.eClass().getName(), currentNamedElement.getName()});
				}
				//���� �������� ������� ������ � ���������� ���������� ������� �� ����� �����
				return ctx.createFailureStatus(new Object[] {currentSysFunc.eClass().getName(), "<��� �����>"});
			}
		}
		//���� �������� �� ������� ������
		return ctx.createSuccessStatus();
	}

}
